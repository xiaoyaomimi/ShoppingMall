package com.mimile.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by caidongdong on 2016/11/28 15:56
 * email : mircaidong@163.com
 */
public abstract class ContentFragment extends Fragment {
    public View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata(savedInstanceState);
    }
    //拿到布局填充器去填充相应的xml,生成指定的view对象
    public abstract View initView(LayoutInflater inflater);
    //view填充数据的操作
    public abstract void initdata(Bundle savedInstanceState);
}
