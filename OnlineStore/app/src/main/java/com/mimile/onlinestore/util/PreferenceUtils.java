package com.mimile.onlinestore.util;

import android.content.Context;

import com.mimile.base.util.BaseSharedPreference;
import com.mimile.onlinestore.app.OnlieStoreApplication;

/**
 * Created by caidongdong on 2016/11/30 17:39
 * email : mircaidong@163.com
 */
public class PreferenceUtils extends BaseSharedPreference {
    private static PreferenceUtils preferenceUtils;

    private PreferenceUtils(Context context) {
        super(context);
    }

    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(OnlieStoreApplication.getContext());
        }
        return preferenceUtils;
    }
}
