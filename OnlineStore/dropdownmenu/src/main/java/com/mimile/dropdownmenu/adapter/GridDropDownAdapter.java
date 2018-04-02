package com.mimile.dropdownmenu.adapter;

import android.content.Context;
import android.widget.TextView;

import com.mimile.dropdownmenu.R;

import java.util.List;


public class GridDropDownAdapter extends DropDownAdapter {

    public GridDropDownAdapter(Context context, List<String> list) {
        super(context,list);
    }

    @Override
    protected int inflateItemView() {
        return R.layout.item_grid_drop_down;
    }

    @Override
    protected void actionSelect(TextView textView) {
        textView.setBackgroundResource(R.drawable.shape_drop_down_check_bg);
    }

    @Override
    protected void actionNotSelect(TextView textView) {
        textView.setBackgroundResource(R.drawable.shape_drop_down_uncheck_bg);
    }
}