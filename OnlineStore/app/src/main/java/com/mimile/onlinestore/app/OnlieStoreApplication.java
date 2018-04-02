package com.mimile.onlinestore.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.mimile.onlinestore.entity.User;

import java.lang.reflect.Field;

/**
 * Created by caidongdong on 2016/11/28 15:08
 * email : mircaidong@163.com
 * 捕获未知崩溃异常写入文件或上传到服务器
 * 以及使用反射替换app文字
 */
public class OnlieStoreApplication extends Application {
    public static Typeface typeFace;
    private static Context context;
    private User user;
    private static OnlieStoreApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();;
        setTypeface();
        instance = this;
    }

    public static OnlieStoreApplication getInstance() {
        return instance;
    }
    /**
     * 通过反射方法设置app全局字体
     */
    public void setTypeface(){
        typeFace = Typeface.createFromAsset(getAssets(), "fonts/fzyh_508.ttf");
        try
        {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, typeFace);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }

    public User getUser() {
        return user;
    }

    public  void setUser(User user) {
        this.user = user;
    }
}
