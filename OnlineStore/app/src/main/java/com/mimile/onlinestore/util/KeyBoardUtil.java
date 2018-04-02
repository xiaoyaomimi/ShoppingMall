package com.mimile.onlinestore.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by caidongdong on 2017/1/6 15:31
 * email : mircaidong@163.com
 */

public class KeyBoardUtil {

    /**
     * 关闭软键盘
     *
     * @param mContext
     */
    public static void hideKeyboard(Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) mContext).getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 判断软件是否显示
     * @param context
     * @return
     */
    public boolean getKeyboardStatus(Context context) {

        return false;
    }
}
