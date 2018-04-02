package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.OrderMultiple;
import com.mimile.onlinestore.view.adapter.OrderCommonAdapter;
import com.mimile.onlinestore.view.adapter.decortion.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class OrderUnpaidFragment extends ContentFragment {
    private RecyclerView recyclerView;
    private List<OrderMultiple> multipleList;
    private OrderCommonAdapter adapter;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_order_child,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.order_fragment_child_rv);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        multipleList = new ArrayList<>();
        multipleList.add(new OrderMultiple());
        multipleList.add(new OrderMultiple());
        adapter = new OrderCommonAdapter(getActivity(),multipleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    }
}
