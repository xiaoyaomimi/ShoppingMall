package com.mimile.onlinestore.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.OrderMultiple;
import com.mimile.onlinestore.view.activity.LogisticsActivity;
import com.mimile.onlinestore.view.customview.ConfirmReceiptDialog;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/20 17:27
 * email : mircaidong@163.com
 */
public class OrderCommonAdapter extends BaseQuickAdapter<OrderMultiple> implements View.OnClickListener {
    private Context context;
    private List<OrderMultiple> orderMultipleList;

    public OrderCommonAdapter(Context context, List<OrderMultiple> data) {
        super(R.layout.list_item_order_fragment,data);
        this.context = context;
        this.orderMultipleList = data;
    }


    @Override
    protected void convert(BaseViewHolder holder, OrderMultiple orderMultiple) {
        holder.getView(R.id.order_fragment_child_item_tv_logistics).setOnClickListener(this);
        holder.getView(R.id.order_fragment_child_item_tv_confirm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.order_fragment_child_item_tv_logistics:
                intent = new Intent(context, LogisticsActivity.class);
                break;
            case R.id.order_fragment_child_item_tv_confirm:
                try {
                    ConfirmReceiptDialog dialog = new ConfirmReceiptDialog(context, "");
                    dialog.show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        if (intent == null)
            return;
        context.startActivity(intent);
    }
}
