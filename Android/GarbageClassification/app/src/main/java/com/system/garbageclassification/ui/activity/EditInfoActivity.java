package com.system.garbageclassification.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.system.garbageclassification.R;
import com.system.garbageclassification.http.HttpClientUtils;
import com.system.garbageclassification.model.User;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.UserInfo;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 编辑个人信息
 */
public class EditInfoActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.nickname_et)
    EditText nicknameEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    private SVProgressHUD dialog;  //进度条
    /**----------变量----------**/
    private User.ResultBean user;  //用户类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_edit_info;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);  //actionbar是toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //toolbar左侧图标显示
        toolbar.setNavigationIcon(R.mipmap.back);  //toolbar左侧图标图片设置
        toolbar.setNavigationOnClickListener(view -> finish());  //图标的点击事件
        dialog = new SVProgressHUD(this);  //初始化进度条
    }

    @Override
    public void loadData() {
        user = (User.ResultBean)getIntent().getSerializableExtra(Constants.USER);  //接收数据
        //设置到对应的控件上
        usernameEt.setText(user.getUsername());  //设置用户名
        nicknameEt.setText(user.getNickname());  //设置昵称
        phoneEt.setText(user.getPhone());  //设置手机号码
    }

    @OnClick(R.id.save_btn)
    public void onViewClicked() {
        if(TextUtils.isEmpty(nicknameEt.getText().toString())
                || TextUtils.isEmpty(phoneEt.getText().toString())){
            dialog.showInfoWithStatus("务必填写所有信息.");
            return;
        }
        if(!RegexUtils.isMobileSimple(phoneEt.getText().toString())){
            ToastUtils.showShort("请输入正确的手机号");
            return;
        }
        //通过数据合法性后
        dialog.showWithStatus("保存修改中");
        HttpClientUtils.getHttpUrl(Constants.HOST).editInfo(nicknameEt.getText().toString()
                , UserInfo.getInstance().getUserId(), phoneEt.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    //隐藏进度条
                    dialog.dismiss();
                    //提示服务端返回的消息
                    ToastUtils.showShort(user.getMessage());
                    //根据code判断有否成功与数据库交互
                    if(user.getCode() == 1){
                        //修改成功，设置回调并返回到上一个界面
                        setResult(Constants.EDIT_INFO,new Intent());
                        finish();
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
}
