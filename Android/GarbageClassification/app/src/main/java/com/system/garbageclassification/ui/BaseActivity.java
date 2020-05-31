package com.system.garbageclassification.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.SharedPreferencesUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 父类Activity，项目中的Activity均继承该类
 */
public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,TextWatcher {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutID());
        bind = ButterKnife.bind(this);
        init(savedInstanceState);
        loadData();
    }

    //布局文件
    public abstract int getLayoutID();

    //初始化类
    public abstract void init(Bundle savedInstanceState);

    //加载数据
    public abstract void loadData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
