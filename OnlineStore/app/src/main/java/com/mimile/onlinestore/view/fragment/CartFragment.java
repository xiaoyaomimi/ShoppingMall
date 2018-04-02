package com.mimile.onlinestore.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.CartWares;
import com.mimile.onlinestore.util.CartWaresCheckCallBack;
import com.mimile.onlinestore.view.activity.UserCommonActivity;
import com.mimile.onlinestore.view.activity.WaresDetialActivity;
import com.mimile.onlinestore.view.adapter.CartAdapter;
import com.mimile.onlinestore.view.adapter.decortion.CardViewtemDecortion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/11/28 16:00
 * email : mircaidong@163.com
 */
public class CartFragment extends ContentFragment implements SwipeRefreshLayout.OnRefreshListener, CompoundButton.OnCheckedChangeListener,CartWaresCheckCallBack,View.OnClickListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<CartWares> cartList;
    private CartAdapter adapter;
    private CheckBox selectAll;
    private boolean ignoreChange = false;
    private TextView submit;
    private TextView totalPrice;
    private int waresNum;
    private float total;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_cart,null);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cart_recyclerview);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rv_cart_refresh_layout);
        selectAll = (CheckBox) view.findViewById(R.id.rv_cart_check_box_select_all_wares);
        submit = (TextView) view.findViewById(R.id.rv_cart_tv_submit);
        totalPrice = (TextView) view.findViewById(R.id.rv_cart_tv_total_price);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        cartList = new ArrayList<>();
        CartWares cartWares1 = new CartWares();
        cartWares1.setStoreId("1");
        cartWares1.setStoreName("天猫旗舰店1");
        cartWares1.setPrice(20f);
        CartWares cartWares3 = new CartWares();
        cartWares3.setStoreId("1");
        cartWares3.setStoreName("天猫旗舰店1");
        cartWares3.setPrice(60f);
        CartWares cartWares2 = new CartWares();
        cartWares2.setStoreId("2");
        cartWares2.setStoreName("天猫旗舰店2");
        cartWares2.setPrice(70f);
        CartWares cartWares4 = new CartWares();
        cartWares4.setStoreId("2");
        cartWares4.setStoreName("天猫旗舰店2");
        cartWares4.setPrice(90f);
        cartList.add(cartWares1);
        cartList.add(cartWares3);
        cartList.add(cartWares2);
        cartList.add(cartWares4);
        recyclerView.addItemDecoration(new CardViewtemDecortion());
        adapter = new CartAdapter(cartList,getActivity(),new int[]{2,2},this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener( ){

            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WaresDetialActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter sadapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rv_cart_item_wares_check:
                        adapter.waresCheckBoxStatusChanged(position,!((CheckBox)view).isChecked());
                        break;
                    case R.id.rv_cart_item_store_check:
                        adapter.storeCheckBoxStatusChanged(position,!((CheckBox)view).isChecked());
                        changeStoreCheckStatus(position,!((CheckBox)view).isChecked());
                        break;
                    case R.id.tv_cart_item_modify:
                        adapter.modifyWarresNum(getViewHolder(position));
                        break;
                    default:
                        break;
                }
            }
        });
        refreshLayout.setOnRefreshListener(this);
        selectAll.setOnCheckedChangeListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (ignoreChange)
            return;
        adapter.selectAllWares(isChecked);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        float tempTotalPrice = 0;
        int tempNum = 0;
        for (int i = 0; i < manager.getItemCount(); i ++) {
            View view = manager.getChildAt(i);
            if (view == null)
                continue;
            BaseViewHolder  holder = (BaseViewHolder)recyclerView.getChildViewHolder(view);
            CheckBox checkBox = holder.getView(R.id.rv_cart_item_store_check);
            if (checkBox.isChecked() != isChecked) {
                checkBox.setChecked(isChecked);
            }
            CheckBox waresCheckBox = holder.getView(R.id.rv_cart_item_wares_check);
            if (waresCheckBox.isChecked() != isChecked) {
                tempTotalPrice += cartList.get(i).getPrice();
                tempNum ++;
            }
        }
        if (tempNum > 0) {
            if (isChecked == false) {
                tempTotalPrice = - tempTotalPrice;
                tempNum = - tempNum;
            }
            changeWaresNumAndTotalPrice(tempTotalPrice, tempNum);
        }
    }

    @Override
    public void storeWaresCheckedAll(int position,boolean status) {
        changeStoreCheckStatus(position,status);
        if (!status) {
            ignoreChange = true;
            selectAll.setChecked(status);
            ignoreChange = false;
        }else {
            checkAllWareCheckBox();
        }
    }

    @Override
    public void showOrHideChildModifyLayout(int startpoisiton, int endposition, boolean status) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        for (int i = startpoisiton; i < endposition; i ++) {
            View view = manager.getChildAt(i);
            if (view == null)
                continue;
            BaseViewHolder  holder = (BaseViewHolder)recyclerView.getChildViewHolder(view);
            LinearLayout linearLayout = holder.getView(R.id.rv_cart_item_wares_desc_layout);
            RelativeLayout relativeLayout = holder.getView(R.id.rv_cart_item_wares_opt_layout);
            if (status) {
                relativeLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.INVISIBLE);
            }else {
                relativeLayout.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void changeWaresNumAndTotalPrice(float price, int num) {
        total += price;
        totalPrice.setText(Float.toString(total));
        waresNum += num;
        submit.setText(getActivity().getResources().getString(R.string.submit)  + "(" + waresNum + ")");
    }


    private void changeStoreCheckStatus(int position,boolean status) {
        BaseViewHolder  holder = getViewHolder(position);
        CheckBox checkBox = holder.getView(R.id.rv_cart_item_store_check);
        if (checkBox.isChecked() != status) {
            adapter.setIgnoreWaresChecked(true);
            checkBox.setChecked(status);
            adapter.setIgnoreWaresChecked(false);
        }
    }

    public BaseViewHolder getViewHolder(int position) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        View view = manager.getChildAt(position);
        if (view == null)
            return null;
        return (BaseViewHolder)recyclerView.getChildViewHolder(view);
    }

    /**
     * 每次选择商品都要检查是否选中了全部商品
     */
    private void checkAllWareCheckBox() {
        boolean allWaresChecked = true;
        for (int i = 0; i < cartList.size(); i ++) {
            if (!cartList.get(i).isChecked()) {
                allWaresChecked = false;
                break;
            }
        }
        if (allWaresChecked) {
            ignoreChange = true;
            selectAll.setChecked(true);
            ignoreChange = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_cart_tv_submit:
                if (submit.getText().toString().equals("结算(0)") && !isAnyWaresChecked()) {
                    Toast.makeText(getActivity(),"您还没有选择宝贝哦！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"结算咯-------",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), UserCommonActivity.class);
                    intent.putExtra("ACTION_VALUE",11);
                    startActivity(intent);
                }

                break;
        }
    }

    private boolean isAnyWaresChecked() {
        for (int i = 0; i < cartList.size(); i ++) {
            if (cartList.get(i).isChecked()) {
                return true;
            }
        }
        return false;
    }
}
