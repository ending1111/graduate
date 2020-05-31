package com.system.garbageclassification.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.system.garbageclassification.tools.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义程序运行类
 */
public class MyApplication extends Application {

    private static Map<Integer,String> typeMap;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);  //初始化工具类
        SharedPreferencesUtil.init(getApplicationContext(),
                getPackageName() + "_preference",
                Context.MODE_PRIVATE);  //初始化SharedPreferencesUtil工具类
        initData();
    }

    /**
     * 垃圾分类类型数据
     */
    private void initData() {
        typeMap = new HashMap<>();
        typeMap.put(1,"干垃圾");
        typeMap.put(2,"湿垃圾");
        typeMap.put(3,"可回收物");
        typeMap.put(4,"有害垃圾");
    }
}
