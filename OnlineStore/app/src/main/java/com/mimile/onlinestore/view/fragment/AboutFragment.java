package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class AboutFragment extends ContentFragment {

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_about_us,null);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {

    }
}
