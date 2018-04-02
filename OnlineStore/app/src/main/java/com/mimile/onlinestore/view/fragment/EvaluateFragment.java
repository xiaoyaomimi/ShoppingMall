package com.mimile.onlinestore.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mimile.base.view.tagview.FlowLayout;
import com.mimile.base.view.tagview.TagAdapter;
import com.mimile.base.view.tagview.TagFlowLayout;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Evaluate;
import com.mimile.onlinestore.view.adapter.EvaluateAdater;
import com.mimile.onlinestore.view.adapter.decortion.CardViewtemDecortion;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by caidongdong on 2017/1/3 17:35
 * email : mircaidong@163.com
 */

public class EvaluateFragment extends Fragment {
    private TagFlowLayout tagFlowLayout;
    private List<String> list;
    private RecyclerView recyclerView;
    private List<Evaluate> evaluates;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate,null);
        View header = inflater.inflate(R.layout.header_evaluate,null);
        tagFlowLayout = (TagFlowLayout) header.findViewById(R.id.fragment_evaluate_fl);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_evaluate_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new CardViewtemDecortion());
        evaluates = new ArrayList<>();
        evaluates.add(new Evaluate());
        evaluates.add(new Evaluate());
        evaluates.add(new Evaluate());
        EvaluateAdater adater = new EvaluateAdater(evaluates);
        adater.addHeaderView(header);
        recyclerView.setAdapter(adater);
        tagFlowLayout.setMaxSelectCount(1);
        tagFlowLayout.setDefaultSelectedPosition(0);
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        initData();
        TagAdapter tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) mInflater.inflate(R.layout.popwin_wares_opt_item,
                        tagFlowLayout, false);
                tv.setText(str);
                return tv;
            }
        };
        tagFlowLayout.setAdapter(tagAdapter);
        return view;

    }

    private void initData() {
        list = new ArrayList<>();
        list.add("全部(15000)");
        list.add("有图(500)");
        list.add("追加(100)");
    }
}
