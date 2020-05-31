package com.system.garbageclassification.ui.activity;

import android.os.Bundle;

import com.system.garbageclassification.R;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_about;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);  //actionbar是toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //toolbar左侧图标显示
        toolbar.setNavigationIcon(R.mipmap.back);  //toolbar左侧图标图片设置
        toolbar.setNavigationOnClickListener(view -> finish());  //图标的点击事件
    }

    @Override
    public void loadData() {

    }
}
