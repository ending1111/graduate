package com.system.garbageclassification.http;

import com.system.garbageclassification.model.Garbage;
import com.system.garbageclassification.model.MResult;
import com.system.garbageclassification.model.News;
import com.system.garbageclassification.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */
public interface ApiStore {

    /**
     * 模糊搜索查询垃圾
     * @param keyword
     * @param pageNo
     * @return
     */
    @GET("searchByKeyword")
    Observable<Garbage> searchByKeyword(@Query("keyword")String keyword,@Query("pageNo")int pageNo);


    /**
     * 请求新闻
     * @param pageNo
     * @return
     */
    @GET("getNewsList")
    Observable<News> getNewsList(@Query("pageNo")int pageNo);


    /**
     * 修改头像
     * @param part
     * @param userId
     * @return
     */
    @Multipart
    @POST("changeAvatar")
    Observable<MResult> changeAvatar(@Part MultipartBody.Part part, @Part("userId") RequestBody userId);



    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("loginApp")
    Observable<MResult> login(@Field("username") String username, @Field("password") String password);


    /**
     * 注册
     * @param username
     * @param nickname
     * @param password
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("register")
    Observable<MResult> register(@Field("username") String username, @Field("nickname") String nickname,
                                 @Field("password") String password, @Field("phone") String phone);


    /**
     * 获取用户
     * @param userId
     * @return
     */
    @GET("getUserById")
    Observable<User> getUserById(@Query("userId") String userId);


    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @FormUrlEncoded
    @POST("changePassword")
    Observable<MResult> changePassword(@Field("userId") String userId, @Field("oldPassword") String oldPassword,
                                       @Field("newPassword") String newPassword);



    @FormUrlEncoded
    @POST("editInfo")
    Observable<User> editInfo(@Field("nickname") String name, @Field("userId") String userId,
                                 @Field("phone") String phone);
}

