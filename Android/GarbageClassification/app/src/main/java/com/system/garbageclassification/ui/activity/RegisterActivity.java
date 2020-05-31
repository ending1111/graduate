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
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.nickname_et)
    EditText nicknameEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.again_password_et)
    EditText againPasswordEt;
    private SVProgressHUD dialog;  //进度条
    /**----------变量----------**/
    private String alphabetRegex = ".*[a-zA-z].*";
    private String numberRegex = ".*[0-9].*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
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

    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(usernameEt.getText().toString())
                || TextUtils.isEmpty(nicknameEt.getText().toString())
                || TextUtils.isEmpty(phoneEt.getText().toString())
                || TextUtils.isEmpty(passwordEt.getText().toString())
                || TextUtils.isEmpty(againPasswordEt.getText().toString())) {
            ToastUtils.showShort("务必填写所有信息.");
            return;
        }
        if(usernameEt.getText().toString().length() < 6){
            ToastUtils.showShort("用户名必须大于等于6个字符.");
            return;
        }
        if(!passwordEt.getText().toString().matches(alphabetRegex)
                || !passwordEt.getText().toString().matches(numberRegex)
                || passwordEt.getText().toString().length() < 6){
            ToastUtils.showShort("密码必须包含字母和数字，且长度大于等于6.");
            return;
        }
        if(!passwordEt.getText().toString()
                .equals(againPasswordEt.getText().toString())){
            ToastUtils.showShort("两次密码输入务必一致");
            return;
        }
        if(!RegexUtils.isMobileSimple(phoneEt.getText().toString())){
            ToastUtils.showShort("请输入正确的手机号");
            return;
        }
        //对所有数据的合法性验证过之后，就进行注册请求
        dialog.showWithStatus("注册中");
        HttpClientUtils.getHttpUrl(Constants.HOST).register(usernameEt.getText().toString(),
                nicknameEt.getText().toString(),passwordEt.getText().toString(),phoneEt.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mResult -> {
                    //隐藏进度条
                    dialog.dismiss();
                    //提示服务端返回的消息
                    ToastUtils.showShort(mResult.getMessage());
                    //根据code判断有否成功与数据库交互
                    if(mResult.getCode() == 1){
                        //注册成功
                        finish();  //返回到上一个界面
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
