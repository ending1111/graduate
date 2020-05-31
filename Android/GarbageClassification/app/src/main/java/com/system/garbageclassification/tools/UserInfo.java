package com.system.garbageclassification.tools;

/**
 * 用户信息
 */
public class UserInfo {

    private static UserInfo info;

    public static UserInfo getInstance(){
        if(info == null)
            info = new UserInfo();
        return info;
    }

    //设置用户id
    public void setUserId(String id){
        SharedPreferencesUtil.getInstance().putString("user_id",id);
    }

    //获取用户id
    public String getUserId(){
        return SharedPreferencesUtil.getInstance().getString("user_id");
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo(){
        SharedPreferencesUtil.getInstance().remove("user_id");
    }

}
