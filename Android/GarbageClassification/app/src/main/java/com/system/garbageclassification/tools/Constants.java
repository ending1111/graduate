package com.system.garbageclassification.tools;

import android.os.Environment;

import java.io.File;

/**
 * 常量
 */
public class Constants {
//120.27.247.226
//    自带的虚拟机 10.0.2.2
//   自己ip地址  192.168.8.146
    //舅舅家ip  192.168.1.105
    //自己手机上的热点：172.20.10.3
    public static final String HOST = "http://172.20.10.3:8080/garbage/";  //服务端ip和端口和地址

    public static final String USER_ID = "user_id";  //用户id

    public static final String TYPE = "type";  //垃圾分类类型

    public static final String GARBAGE = "garbage";  //垃圾

    public static final String NEWS = "news";  //新闻

    public static final String USER = "user";  //用户

    public static final int LOGIN = 1;  //登录

    public static final int EDIT_INFO = 2;  //编辑个人信息

    public static final int LOGOUT = 3;  //退出登录

    /**
     * 获取Luban提供的图片路径
     * @return
     */
    public static String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Garbage/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

}
