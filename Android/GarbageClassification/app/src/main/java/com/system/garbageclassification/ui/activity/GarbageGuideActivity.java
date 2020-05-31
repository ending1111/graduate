package com.system.garbageclassification.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.system.garbageclassification.R;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.ui.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

/**
 * 指南
 */
public class GarbageGuideActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.guide_bg)
    ImageView guideBg;
    /**----------变量----------**/
    private int type = 0;  //垃圾分类类型，1干垃圾、2湿垃圾、3可回收物、4有害垃圾

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_garbage_guide;
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
        type = getIntent().getIntExtra(Constants.TYPE, 0);
        switch (type) {
            case 1:
                //干垃圾
                Picasso.get().load(R.mipmap.ganlaji_bg).into(guideBg);
                break;
            case 2:
                //湿垃圾
                Picasso.get().load(R.mipmap.shilaji_bg).into(guideBg);
                break;
            case 3:
                //可回收物
                Picasso.get().load(R.mipmap.kehuishouwu_bg).into(guideBg);
                break;
            case 4:
                //有害垃圾
                Picasso.get().load(R.mipmap.youhailaji_bg).into(guideBg);
                break;
        }
    }
}
