package com.mimile.base.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by caidongdong on 2016/11/30 17:16
 * email : mircaidong@163.com
 * 操作shareprefrefernce基类
 *
 */
public class BaseSharedPreference {
    private Context context;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String FILE_NAME = "globe_config";

    public BaseSharedPreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public BaseSharedPreference(Context context, String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            this.context = context;
            sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }else {
           new BaseSharedPreference(context);
        }
    }

    public void setString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }

    public void setBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void setInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }
}
