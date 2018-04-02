package com.mimile.onlinestore.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Wares;
import com.mimile.onlinestore.view.activity.AddressActivity;
import com.mimile.onlinestore.view.adapter.ConfirmOderAdapter;
import com.mimile.onlinestore.view.adapter.decortion.DividerItemDecoration;
import com.mimile.onlinestore.view.customview.ConfirmPayDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class ConfirmOrderFragment extends ContentFragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private LinearLayout selectAddress;
    private ConfirmOderAdapter adapter;
    private List<Wares> list;
    private int[] range;
    private View header;
    private TextView confirmOrder;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_confirm_order,null);
        header = inflater.inflate(R.layout.confirm_order_select_address_header,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.confirm_order_rv);
        selectAddress = (LinearLayout) header.findViewById(R.id.confirm_order_select_address);
        confirmOrder = (TextView) view.findViewById(R.id.confirm_order_confirm);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        list = new ArrayList<>();
        Wares wares = new Wares();
        wares.setStoreId("1");
        Wares wares0 = new Wares();
        wares0.setStoreId("1");
        Wares wares1 = new Wares();
        wares1.setStoreId("2");
        Wares wares2 = new Wares();
        wares2.setStoreId("3");
        Wares wares3 = new Wares();
        wares3.setStoreId("3");
        Wares wares4 = new Wares();
        wares4.setStoreId("3");
        list.add(wares);
        list.add(wares0);
        list.add(wares1);
        list.add(wares2);
        list.add(wares3);
        list.add(wares4);
        range = new int[]{2,1,3};
        adapter = new ConfirmOderAdapter(getActivity(),list,range);
        adapter.addHeaderView(header);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayout.VERTICAL));
        selectAddress.setOnClickListener(this);
        confirmOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_order_confirm:
                ConfirmPayDialog dialog = new ConfirmPayDialog(getActivity());
                dialog.show();
                break;
            case R.id.confirm_order_select_address:
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
