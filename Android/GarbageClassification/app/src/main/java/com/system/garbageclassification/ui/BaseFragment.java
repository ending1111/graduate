package com.system.garbageclassification.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.SharedPreferencesUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment继承该类
 */

public abstract class BaseFragment extends Fragment {

    protected View view;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutID(), container, false);
            bind = ButterKnife.bind(this,view);
            initView(savedInstanceState);
        }
        return view;
    }

    /**
     * 获取布局文件
     */
    protected abstract int getLayoutID();
    /**
     * 初始化数据
     * 绑定等操作
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
