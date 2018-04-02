package com.mimile.onlinestore.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.CartWares;
import com.mimile.onlinestore.util.CartWaresCheckCallBack;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/14 14:36
 * email : mircaidong@163.com
 */
public class CartAdapter extends BaseQuickAdapter<CartWares> {
    private Context context;
    private List<CartWares> waresList;
    private int[] range;
    private CartWaresCheckCallBack callBack;
    private boolean ignoreWaresChecked = false;

    public CartAdapter(List<CartWares> data, Context context, int[] range, CartWaresCheckCallBack callBack) {
        super(R.layout.list_item_cart_wares_item, data);
        this.context = context;
        waresList = data;
        this.range = range;
        this.callBack = callBack;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final CartWares cartWares) {
        final int position = holder.getLayoutPosition();
        if (position > 0 && getItem(position - 1).getStoreId().equals(getItem(position).getStoreId())) {
            holder.getView(R.id.rv_cart_item_store_head).setVisibility(View.GONE);
            holder.getView(R.id.rv_cart_item_middle_line).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.rv_cart_item_store_head).setVisibility(View.VISIBLE);
            ((TextView) holder.getView(R.id.rv_cart_item_store_name)).setText(cartWares.getStoreName());
        }
        holder.addOnClickListener(R.id.rv_cart_item_wares_check);
        holder.addOnClickListener(R.id.rv_cart_item_store_check);
        holder.addOnClickListener(R.id.tv_cart_item_modify);
        ((CheckBox) holder.getView(R.id.rv_cart_item_wares_check)).setChecked(cartWares.isChecked());
        ((TextView) holder.getView(R.id.tv_cart_item_modify)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ((TextView)holder.getView(R.id.rv_cart_item_wares_sell_price)).setText("￥" + cartWares.getPrice().toString());

    }

    public void selectAllWares(boolean status) {
        if (status) {
            for (CartWares wares : waresList) {
                wares.setIsChecked(true);
            }
        } else {
            for (CartWares wares : waresList) {
                wares.setIsChecked(false);
            }
        }
        notifyDataSetChanged();
    }

    private void showOrHideModifyLayout(BaseViewHolder holder,boolean status) {
        int position = holder.getLayoutPosition();
        int sum = 0;
        for (int i = 0; i < range.length; i++) {
            sum += range[i];
            if (position < sum) {
                if (1 == sum - range[i])
                    break;
                callBack.showOrHideChildModifyLayout(position,sum,status);
                break;
            }
        }
    }

    public boolean isIgnoreWaresChecked() {
        return ignoreWaresChecked;
    }

    public void setIgnoreWaresChecked(boolean ignoreWaresChecked) {
        this.ignoreWaresChecked = ignoreWaresChecked;
    }

    public void waresCheckBoxStatusChanged(int cbPosition,boolean isChecked) {
        if (isChecked != waresList.get(cbPosition).isChecked())
            waresList.get(cbPosition).setIsChecked(isChecked);
        int sum = 0;
        for (int i = 0; i < range.length; i++) {
            sum += range[i];
            if (cbPosition < sum) {
                boolean isAllChecked = true;
                for (int j = sum - range[i]; j < sum; j++) {
                    if (!waresList.get(j).isChecked()) {
                        isAllChecked = false;
                        break;
                    }
                }
                callBack.storeWaresCheckedAll(sum - range[i], isAllChecked);
                if (isChecked) {
                    callBack.changeWaresNumAndTotalPrice(waresList.get(cbPosition).getPrice(),1);
                }else {
                    callBack.changeWaresNumAndTotalPrice(- waresList.get(cbPosition).getPrice(),- 1);
                }
                break;
            }
        }
    }

    public void storeCheckBoxStatusChanged(int cbPosition, boolean isChecked) {
        if (ignoreWaresChecked)
            return;
        int sum = 0;
        for (int i = 0; i < range.length; i ++) {
            sum += range[i];
            if (cbPosition < sum) {
                for (int j = sum - range[i]; j < sum; j ++) {
                    if (waresList.get(j).isChecked() != isChecked) {
                        waresList.get(j).setIsChecked(isChecked);
                        if (isChecked) {
                            callBack.changeWaresNumAndTotalPrice(waresList.get(j).getPrice(),1);
                        }else {
                            callBack.changeWaresNumAndTotalPrice(- waresList.get(j).getPrice(),- 1);
                        }
                    }

                }
                notifyDataSetChanged();
                break;
            }
        }
    }

    public void modifyWarresNum(BaseViewHolder holder) {
        if (null == holder)
            return;
        if (((TextView) holder.getView(R.id.tv_cart_item_modify)).getText().toString().equals(context.getResources().getString(R.string.modify))) {
            holder.getView(R.id.rv_cart_item_wares_desc_layout).setVisibility(View.INVISIBLE);
            holder.getView(R.id.rv_cart_item_wares_opt_layout).setVisibility(View.VISIBLE);
            ((TextView) holder.getView(R.id.tv_cart_item_modify)).setText(R.string.done);
            showOrHideModifyLayout(holder,true);
        } else {
            holder.getView(R.id.rv_cart_item_wares_desc_layout).setVisibility(View.VISIBLE);
            holder.getView(R.id.rv_cart_item_wares_opt_layout).setVisibility(View.INVISIBLE);
            ((TextView) holder.getView(R.id.tv_cart_item_modify)).setText(R.string.modify);
            showOrHideModifyLayout(holder,false);
        }
    }
}
