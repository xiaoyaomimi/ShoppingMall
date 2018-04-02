package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class OrderFragment extends ContentFragment {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private int[] tabTitles;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_order,null);
        tableLayout = (TabLayout ) view.findViewById(R.id.order_fragment_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.order_fragment_viewpager);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        tabTitles = new int[]{R.string.order_all,R.string.order_unpaid,R.string.order_undelivered,R.string.order_receipt,R.string.order_not_evaluate};
        fragmentList = new ArrayList<>();
        fragmentList.add(new OrderAllFragment());
        fragmentList.add(new OrderUnpaidFragment());
        fragmentList.add(new OrderUnDeliveredFragment());
        fragmentList.add(new OrderReceiptFragment());
        fragmentList.add(new OrderNotEvaluateFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return tabTitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getActivity().getResources().getString(tabTitles[position]);
            }
        });
        int position = getActivity().getIntent().getIntExtra("DEFALUAT_POSITION",0);
        viewPager.setCurrentItem(position);
        tableLayout.setupWithViewPager(viewPager);
    }
}
