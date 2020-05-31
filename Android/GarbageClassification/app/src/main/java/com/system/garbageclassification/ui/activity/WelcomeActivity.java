package com.system.garbageclassification.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.system.garbageclassification.R;
import com.system.garbageclassification.ui.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //沉浸式标题栏
        if (Build.VERSION.SDK_INT >= 21) {  //大于等于5.0系统才能使用
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);  //设置成透明
        }
    }

    @Override
    public void loadData() {
        //在该界面停留3s，再跳转到主界面
        new Thread(() -> {
            try{
                Thread.sleep(3000);
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
