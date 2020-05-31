package com.system.garbageclassification.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.system.garbageclassification.R;
import com.system.garbageclassification.tools.Constants;
import com.system.garbageclassification.tools.mLocalGlideImageLoader;
import com.system.garbageclassification.ui.BaseFragment;
import com.system.garbageclassification.ui.activity.GarbageGuideActivity;
import com.system.garbageclassification.ui.activity.SearchActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    /**
     * ----------控件----------
     **/
    @BindView(R.id.banner_home)
    Banner banner;
    /**
     * ----------变量----------
     **/
    private List<Integer> bannerList = new ArrayList<>();  //存放轮播图的集合

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadData();  //加载数据
        initBanner();  //初始化轮播图
    }

    /**
     * 初始化轮播图
     */
    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new mLocalGlideImageLoader());
        //设置图片集合
        banner.setImages(bannerList);
        //设置ban效果ner动画
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        bannerList.add(R.mipmap.banner1);
        bannerList.add(R.mipmap.banner2);
        bannerList.add(R.mipmap.banner3);
        bannerList.add(R.mipmap.banner4);
        bannerList.add(R.mipmap.banner5);
    }

    @OnClick({R.id.recycle_garbage, R.id.harmful_garbage, R.id.wet_garbage, R.id.dry_garbage})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), GarbageGuideActivity.class);
        switch (view.getId()) {
            case R.id.recycle_garbage:
                intent.putExtra(Constants.TYPE,3);
                break;
            case R.id.harmful_garbage:
                intent.putExtra(Constants.TYPE,4);
                break;
            case R.id.wet_garbage:
                intent.putExtra(Constants.TYPE,2);
                break;
            case R.id.dry_garbage:
                intent.putExtra(Constants.TYPE,1);
                break;
        }
        startActivity(intent);
    }

    /**
     * 点击搜索
     */
    @OnClick(R.id.to_search)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }
}
