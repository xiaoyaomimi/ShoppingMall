package com.mimile.base.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by caidongdong on 2016/11/28 16:08
 * email : mircaidong@163.com
 */
public class MyViewPager extends ViewPager{
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public MyViewPager(Context context) {
        super(context);
    }
    //自定义的viewpager不要去拦截相应的事件，传递给内部控件去消费
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
