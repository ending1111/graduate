package com.system.garbageclassification.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.system.garbageclassification.R;
import com.system.garbageclassification.http.HttpClientUtils;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.SharedPreferencesUtil;
import com.system.garbageclassification.tools.UserInfo;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    private SVProgressHUD dialog;  //进度条

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
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

    @OnClick({R.id.login_btn, R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (TextUtils.isEmpty(usernameEt.getText().toString())
                        || TextUtils.isEmpty(passwordEt.getText().toString())) {
                    ToastUtils.showShort("输入用户名和密码后再试.");
                    return;
                }
                //进行登录
                dialog.showWithStatus("登录中");
                HttpClientUtils.getHttpUrl(Constants.HOST).login(usernameEt.getText().toString()
                        ,passwordEt.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mResult -> {
                            //隐藏进度条
                            dialog.dismiss();
                            //提示服务端返回的消息
                            ToastUtils.showShort(mResult.getMessage());
                            //根据code判断有否成功与数据库交互
                            if(mResult.getCode() == 1){
                                //登录成功
                                UserInfo.getInstance().setUserId(mResult.getResult());
                                setResult(Constants.LOGIN,new Intent());  //设置回调
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
                break;
            case R.id.register_btn:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
}
