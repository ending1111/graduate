package com.system.garbageclassification.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.system.garbageclassification.R;
import com.system.garbageclassification.ui.BaseActivity;
import com.system.garbageclassification.ui.BaseFragment;
import com.system.garbageclassification.ui.fragment.CentralFragment;
import com.system.garbageclassification.ui.fragment.HomeFragment;
import com.system.garbageclassification.ui.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    /**----------控件----------**/
    @BindView(R.id.bottom_navigationBar)
    BottomNavigationBar bottomNavigationBar;
    /**----------变量----------**/
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private CentralFragment centralFragment;
    private List<BaseFragment> fragmentList = new ArrayList<>();  //存放Fragment的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //初始化底部导航栏
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);  //设置BottomNavigationBar模式
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);  //设置BottomNavigationBar背景风格
        bottomNavigationBar.setBarBackgroundColor(R.color.white);  //设置BottomNavigationBar背景颜色
        //设置每一个item的图标文字颜色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.shouye_blue, "首页").setInactiveIconResource(R.mipmap.shouye_grey)
                .setActiveColor("#1296db")).setInActiveColor("#8a8a8a")
                .addItem(new BottomNavigationItem(R.mipmap.xinwen_blue, "新闻").setInactiveIconResource(R.mipmap.xinwen_grey)
                        .setActiveColor("#1296db")).setInActiveColor("#8a8a8a")
                .addItem(new BottomNavigationItem(R.mipmap.wode_blue, "我的").setInactiveIconResource(R.mipmap.wode_grey))
                .setActiveColor("#1296db").setInActiveColor("#8a8a8a")
                .setFirstSelectedPosition(0)
                .initialise();
        onTabSelected(2);  //预加载个人信息
        onTabSelected(0);  //默认选择第一个
        bottomNavigationBar.selectTab(0);  //默认显示第一个
        bottomNavigationBar.setTabSelectedListener(this);  //设置点击监听
    }

    /**
     * 底部导航栏的选择监听事件
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();  //Fragment管理类
        FragmentTransaction transaction = fm.beginTransaction();  //Fragment提交类
        hideFragment(transaction);  //隐藏所有Fragment，然后下面开始按点击的item来显示Fragment
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    //实例化首页
                    homeFragment = new HomeFragment();
                    //添加到事件中
                    transaction.add(R.id.frame, homeFragment);
                    //添加到集合中
                    fragmentList.add(homeFragment);
                } else {
                    //直接展示这个Fragment
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (newsFragment == null) {
                    //实例化新闻页
                    newsFragment = new NewsFragment();
                    //添加到事件中
                    transaction.add(R.id.frame, newsFragment);
                    //添加到集合中
                    fragmentList.add(newsFragment);
                } else {
                    //直接展示这个Fragment
                    transaction.show(newsFragment);
                }
                break;
            case 2:
                if (centralFragment == null) {
                    //实例化我的页
                    centralFragment = new CentralFragment();
                    //添加到事件中
                    transaction.add(R.id.frame, centralFragment);
                    //添加到集合中
                    fragmentList.add(centralFragment);
                } else {
                    //直接展示这个Fragment
                    transaction.show(centralFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有
     * @param transaction
     */
    public void hideFragment(FragmentTransaction transaction) {
        for (Fragment fragment : fragmentList) {
            transaction.hide(fragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loadData() {

    }
}
