package com.mimile.onlinestore.handler;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.lang.Thread.UncaughtExceptionHandler;

/**
 * Created by caidongdong on 2016/11/30 17:06
 * email : mircaidong@163.com
 * 当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.并根据网络情况上传至服务器
 */
public class CrashHandler implements UncaughtExceptionHandler{
    public static final String TAG = "mmloo";
    // CrashHandler 实例
    private static CrashHandler instance = new CrashHandler();
    // 程序的 Context 对象
    private Context mContext;
    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();
    // 用来显示Toast中的信息
    private static String error = "程序错误，额，不对，我应该说，服务器正在维护中，请稍后再试";
    private static final Map<String, String> regexMap = new HashMap<String, String>();
    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-cmp_customer_actionbar",
            Locale.CHINA);

    public CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        initHashMap();
        return instance;
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

    }

    private static void initHashMap() {

    }
}
