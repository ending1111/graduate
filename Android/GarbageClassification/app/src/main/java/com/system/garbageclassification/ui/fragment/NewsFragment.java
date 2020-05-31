package com.system.garbageclassification.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.system.garbageclassification.R;
import com.system.garbageclassification.adapter.NewsAdapter;
import com.system.garbageclassification.http.HttpClientUtils;
import com.system.garbageclassification.model.Garbage;
import com.system.garbageclassification.model.News;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.ui.BaseFragment;
import com.system.garbageclassification.ui.activity.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页
 */
public class NewsFragment extends BaseFragment {

    @BindView(R.id.recycler_news)
    RecyclerView recyclerNews;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private NewsAdapter adapter;  //新闻列表适配器
    List<News.ResultBean> newsList = new ArrayList<>();  //新闻数据集合
    private int pageNo = 0;  //分页页数

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //初始化列表
        recyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerNews.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter = new NewsAdapter(R.layout.item_news,newsList);  //初始化适配器
        recyclerNews.setAdapter(adapter);  //列表绑定适配器
        //列表点击事件
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            //进入新闻详情
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra(Constants.NEWS,newsList.get(position));
            startActivity(intent);
        });
        //下拉刷新
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNo = 0;
            newsList.clear();
            loadData();
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageNo++;
            loadData();
        });
        loadData();
    }

    /**
     * 请求新闻
     */
    private void loadData() {
        //进行网络请求
        HttpClientUtils.getHttpUrl(Constants.HOST).getNewsList(pageNo)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
                    refreshLayout.finishRefresh();  //结束刷新
                    refreshLayout.finishLoadMore();  //结束加载更多
                    //根据code判断有否成功与数据库交互
                    if(news.getCode() == 1){
                        List<News.ResultBean> data = news.getResult();
                        if(data.size() > 0){
                            newsList.addAll(data);
                            adapter.notifyDataSetChanged();
                            //如果这次获得的数据条数小于10，证明后面没新的数据了，就不允许上拉加载
                            if(data.size() < 10){
                                LogUtils.e("到这里啦");
                                refreshLayout.setEnableLoadMore(false);
                            }else {
                                refreshLayout.setEnableLoadMore(true);
                            }
                        }
                    }
                }, throwable -> {
                    refreshLayout.finishRefresh();  //结束刷新
                    refreshLayout.finishLoadMore();  //结束加载更多
                    //查看失败的原因
                    LogUtils.e(throwable.getMessage());
                    //提示用户失败的原因
                    ToastUtils.showShort(throwable.getMessage());
                });
    }
}
