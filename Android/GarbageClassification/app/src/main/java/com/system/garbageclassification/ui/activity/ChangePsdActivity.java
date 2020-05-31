package com.system.garbageclassification.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.blankj.utilcode.util.LogUtils;
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
 * 修改密码
 */
public class ChangePsdActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.old_password_et)
    EditText oldPasswordEt;
    @BindView(R.id.new_password_et)
    EditText newPasswordEt;
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
        return R.layout.activity_change_psd;
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

    @OnClick(R.id.save_btn)
    public void onViewClicked() {
        if(TextUtils.isEmpty(oldPasswordEt.getText().toString())
                || TextUtils.isEmpty(newPasswordEt.getText().toString())
                || TextUtils.isEmpty(againPasswordEt.getText().toString())){
            dialog.showInfoWithStatus("务必填写所有信息.");
            return;
        }
        if(!newPasswordEt.getText().toString().matches(alphabetRegex)
                || !newPasswordEt.getText().toString().matches(numberRegex)
                || newPasswordEt.getText().toString().length() < 6){
            ToastUtils.showShort("密码必须包含字母和数字，且长度大于等于6.");
            return;
        }
        if(!newPasswordEt.getText().toString()
                .equals(againPasswordEt.getText().toString())){
            ToastUtils.showShort("两次密码输入务必一致");
            return;
        }
        //对所有数据的合法性验证过之后，就进行修改密码请求
        dialog.showWithStatus("保存中");
        HttpClientUtils.getHttpUrl(Constants.HOST).changePassword(UserInfo.getInstance().getUserId(),
                oldPasswordEt.getText().toString(),newPasswordEt.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mResult -> {
                    //隐藏进度条
                    dialog.dismiss();
                    //提示服务端返回的消息
                    ToastUtils.showShort(mResult.getMessage());
                    //根据code判断有否成功与数据库交互
                    if(mResult.getCode() == 1){
                        //修改成功
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
