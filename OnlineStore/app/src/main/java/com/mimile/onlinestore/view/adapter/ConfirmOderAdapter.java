package com.mimile.onlinestore.view.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Wares;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/19 16:11
 * email : mircaidong@163.com
 */
public class ConfirmOderAdapter extends BaseQuickAdapter<Wares> {
    private Context context;
    private List<Wares> list;
    private int[] range;
    private boolean hideTitle = false;

    public ConfirmOderAdapter(Context context, List<Wares> list, int[] range) {
        super(R.layout.list_item_confirm_order,list);
        this.context = context;
        this.list = list;
        this.range = range;
    }


    @Override
    protected void convert(BaseViewHolder holder, Wares wares) {
        int position = holder.getLayoutPosition();
        int sum = 0;
        for (int i = 0; i < range.length; i ++ ) {
            sum += range[i];
            if (position <= sum) {
                if (sum - position == range[i] - 1) {
                    if (range[i] == 1) {
                        holder.getView(R.id.confirm_order_item_title_ll).setVisibility(View.VISIBLE);
                        holder.getView(R.id.confirm_order_item_detail_ll).setVisibility(View.VISIBLE);
                    }else {
                        holder.getView(R.id.confirm_order_item_title_ll).setVisibility(View.VISIBLE);
                        holder.getView(R.id.confirm_order_item_detail_ll).setVisibility(View.GONE);
                    }
                } else if (sum - position > 0 && sum - position < range[i] - 1) {
                    holder.getView(R.id.confirm_order_item_title_ll).setVisibility(View.GONE);
                    holder.getView(R.id.confirm_order_item_detail_ll).setVisibility(View.GONE);
                }else if (sum - position == 0) {
                    holder.getView(R.id.confirm_order_item_title_ll).setVisibility(View.GONE);
                    holder.getView(R.id.confirm_order_item_detail_ll).setVisibility(View.VISIBLE);
                }
                break;
            }
        }
        if (position == list.size()) {
            holder.getView(R.id.confirm_order_item_opt_ll).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.confirm_order_item_opt_ll).setVisibility(View.GONE);
        }
    }
}
