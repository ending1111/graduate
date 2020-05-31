package com.system.garbageclassification.http;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit网络请求封装类
 */

public class HttpClientUtils {

    private static HttpClientUtils clientData;

    public HttpClientUtils(){}

    private static HttpClientUtils getInstance(){
        if(clientData == null){
            synchronized (HttpClientUtils.class){
                if (clientData == null){
                    clientData = new HttpClientUtils();
                }
            }
        }
        return clientData;
    }

    private static Map<String, Retrofit> retroList = new HashMap<>(); //记录baseUrl和retrofit
    private static Retrofit mRetro = null;

    /**
     *请求地址
     * @param http
     * @return
     */
    private static Retrofit getHttpClient(String http){

        if(!onCheckUrl(http)){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message ->
                    Log.e("Tag","message--->" + message)).setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);
            mRetro = new Retrofit.Builder().addConverterFactory(new NullOnEmptyConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory
                            (RxJava2CallAdapterFactory.create()).baseUrl(http).client
                            (okhttpClient.build()).build();
            //用来将从后台获取到json键值对转换成对象
            retroList.put(http,mRetro);
        }
        return mRetro;
    }

    public static ApiStore getHttpUrl(String http){
        return getHttpClient(http).create(ApiStore.class);
    }

    private static boolean onCheckUrl(String http){
        for(String url : retroList.keySet()){
            if(url.equals(http)){
                mRetro = retroList.get(url);
                return true;
            }
        }
        return false;
    }

}
