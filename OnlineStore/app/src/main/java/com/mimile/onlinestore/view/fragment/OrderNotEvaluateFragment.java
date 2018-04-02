package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.OrderMultiple;
import com.mimile.onlinestore.view.adapter.OrderCommonAdapter;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class OrderNotEvaluateFragment extends ContentFragment {
    private RecyclerView recyclerView;
    private List<OrderMultiple> multipleList;
    private OrderCommonAdapter adapter;
    private RelativeLayout nothing;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_order_child,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.order_fragment_child_rv);
        nothing = (RelativeLayout) view.findViewById(R.id.order_fragment_child_nothing_rl);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        if (multipleList == null || multipleList.isEmpty()) {
            nothing.setVisibility(View.VISIBLE);
            return;
        }
    }
}
