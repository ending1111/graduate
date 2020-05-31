package com.system.garbageclassification.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.system.garbageclassification.R;
import com.system.garbageclassification.adapter.GarbageAdapter;
import com.system.garbageclassification.adapter.HistoryAdapter;
import com.system.garbageclassification.http.HttpClientUtils;
import com.system.garbageclassification.model.Garbage;
import com.system.garbageclassification.model.History;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.MyDatabaseHelper;
import com.system.garbageclassification.tools.UserInfo;
import com.system.garbageclassification.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_result)
    RecyclerView recyclerResult;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.no_data)
    TextView noData;
    @BindView(R.id.recycler_history)
    RecyclerView recyclerHistory;
    @BindView(R.id.history_view)
    LinearLayout historyView;
    @BindView(R.id.result_view)
    RelativeLayout resultView;
    private SVProgressHUD dialog;  //进度条
    /**----------变量----------**/
    private MyDatabaseHelper helper;  //数据库帮助类
    private SQLiteDatabase sd;  //数据库操作类
    private List<History> historyList = new ArrayList<>();  //历史搜索记录数据集合
    private HistoryAdapter hAdapter;  //历史搜索记录的列表适配器
    private List<Garbage.ResultBean> garbageList = new ArrayList<>();  //搜索成功的垃圾数据集合
    private GarbageAdapter gAdapter;  //垃圾列表适配器
    private int pageNo = 0;  //请求页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);  //actionbar是toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //toolbar左侧图标显示
        toolbar.setNavigationIcon(R.mipmap.back);  //toolbar左侧图标图片设置
        toolbar.setNavigationOnClickListener(view -> finish());  //图标的点击事件
        //初始化本地数据库的调用
        //初始化数据库
        helper = new MyDatabaseHelper(this, "garbage.db", null, 1);
        sd = helper.getWritableDatabase();
        dialog = new SVProgressHUD(this);  //初始化进度条
        //初始化历史搜索记录列表
        recyclerHistory.setLayoutManager(new LinearLayoutManager(this));
        hAdapter = new HistoryAdapter(R.layout.item_history, historyList);
        recyclerHistory.setAdapter(hAdapter);
        //点击历史，进入详情
        hAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(SearchActivity.this,GarbageDetailActivity.class);
            History history = historyList.get(position);
            Garbage.ResultBean garbage = new Garbage.ResultBean(history.getName(),
                    history.getType(),history.getFunction());  //将历史转换为垃圾
            intent.putExtra(Constants.GARBAGE,garbage);  //将垃圾类传过去
            startActivity(intent);
        });
        //初始化垃圾搜索结果列表
        recyclerResult.setLayoutManager(new LinearLayoutManager(this));
        gAdapter = new GarbageAdapter(R.layout.item_garbage, garbageList);
        recyclerResult.setAdapter(gAdapter);
        //点击垃圾，进入详情页
        gAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Intent intent = new Intent(SearchActivity.this,GarbageDetailActivity.class);
            intent.putExtra(Constants.GARBAGE,garbageList.get(position));  //将垃圾类传过去
            startActivity(intent);
        });
        //初始化上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNo++;
            loadData();
        });
        contentEt.addTextChangedListener(this);  //给输入框加输入监听
    }

    /**
     * 从本地数据库加载历史记录
     */
    @Override
    public void loadData() {
        historyList.clear();
        Cursor cursor = sd.query("t_history", null, null,null,
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String function = cursor.getString(cursor.getColumnIndex("function"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                historyList.add(new History(id, type, name,function));  //每循环一次，就将这个对象添加到集合
            } while (cursor.moveToNext());
        }
        cursor.close();
        hAdapter.notifyDataSetChanged();  //通知适配器刷新
        //判断当前是否有历史记录
        if (historyList.size() > 0) {
            historyView.setVisibility(View.VISIBLE);
        } else {
            historyView.setVisibility(View.GONE);
        }
    }

    /**
     * 网络请求-根据
     */
    private void searchGarbage() {
        dialog.showWithStatus("搜索中");
        //进行网络请求关键词搜索垃圾
        HttpClientUtils.getHttpUrl(Constants.HOST).searchByKeyword(contentEt.getText().toString(),pageNo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(garbage -> {
                    refreshLayout.finishLoadMore();  //结束加载更多
                    //隐藏进度条
                    dialog.dismiss();
                    //根据code判断有否成功与数据库交互
                    if(garbage.getCode() == 1){
                        List<Garbage.ResultBean> data = garbage.getResult();
                        if(data.size() > 0){
                            garbageList.addAll(data);
                            gAdapter.notifyDataSetChanged();
                            noData.setVisibility(View.GONE);
                            //如果这次获得的数据条数小于10，证明后面没新的数据了，就不允许上拉加载
                            if(data.size() < 10){
                                refreshLayout.setEnableLoadMore(false);
                            }else {
                                refreshLayout.setEnableLoadMore(true);
                            }
                        }else if (data.size() == 0){
                            noData.setVisibility(View.VISIBLE);
                        }
                    }
                }, throwable -> {
                    //隐藏进度条
                    dialog.dismiss();
                    //查看失败的原因
                    LogUtils.e(throwable.getMessage());
                    //提示用户失败的原因
                    ToastUtils.showShort(throwable.getMessage());
                });
    }

    @OnClick({R.id.search_btn, R.id.delete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_btn:
                //点击搜索后，根据输入框的内容去搜索
                if(TextUtils.isEmpty(contentEt.getText().toString())){
                    ToastUtils.showShort("关键词不能为空!");
                    return;
                }
                garbageList.clear();
                gAdapter.notifyDataSetChanged();
                pageNo = 0;
                searchGarbage();
                break;
            case R.id.delete_btn:
                //清空历史搜索
                new AlertDialog.Builder(this)
                        .setTitle("警告")
                        .setMessage("是否确定要删除所有历史搜索记录？删除后不可恢复！")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确认删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sd.delete("t_history",null,null);
                                loadData();
                            }
                        }).create().show();
                break;
        }
    }

    /**
     * 判断输入框的内容长度，为0的话，就显示历史记录
     *
     * @param charSequence
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (contentEt.getText().toString().length() == 0) {
            historyView.setVisibility(View.VISIBLE);
            loadData();  //重新获取历史记录
            //清空搜索出来的记录
            garbageList.clear();
            gAdapter.notifyDataSetChanged();
            noData.setVisibility(View.GONE);
        }else {
            historyView.setVisibility(View.GONE);
        }
    }

    /**
     * 页面重新出现的时候，加载历史列表
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        if(TextUtils.isEmpty(contentEt.getText().toString())){
            loadData();
        }
    }

    //    /**
//     * 展示没有数据的图标
//     */
//    private void showNoData(){
//        if(garbageList.size() == 0){
//            noData.setVisibility(View.VISIBLE);
//        }else {
//            noData.setVisibility(View.GONE);
//        }
//    }
}
