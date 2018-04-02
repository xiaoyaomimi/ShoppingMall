package com.mimile.onlinestore.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Func1;

/**
 * Created by caidongdong on 2016/12/2 11:58
 * email : mircaidong@163.com
 * 所有网络交互服务接口管理类
 */
public class ApiManager {
//    private static final String BASE_URL = "http://221.228.197.122/mobile/";
    private static final String BASE_URL = "http://112.124.22.238:8081/course_api/";
    private static final String TO_CHARSET_NAME = "GB2312";
    private static ApiManager apiManager;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Func1<ResponseBody, String> transformCharset;

    //网络请求回调接口
    public IUserService userService;

    private ApiManager() {
        initObject();
    }

    public static ApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new ApiManager();
        }
        return apiManager;
    }

    private void initObject() {
        //初始化okhttp网络框架
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) )
                .build();
        //初始化gson转换框架
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();//使用 gson coverter，统一日期请求格式
        //初始化retrofit框架
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        //网络请求具体服务
        userService = retrofit.create(IUserService.class);
    }
}
