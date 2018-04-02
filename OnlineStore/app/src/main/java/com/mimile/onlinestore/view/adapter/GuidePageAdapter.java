package com.mimile.onlinestore.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by caidongdong on 2016/11/30 15:48
 * email : mircaidong@163.com
 */
public class GuidePageAdapter extends PagerAdapter {
    private List<View> views;

    public GuidePageAdapter(List<View> views) {
        this.views = views;
    }

    //销毁arg1位置的界面
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public int getCount() {
        if (views != null && views.size() > 0)
        {
            return views.size();
        }
        return 0;
    }

    //判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    //初始化arg1位置的界面
    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        return views.get(arg1);
    }
}
