package com.mimile.dropdownmenu.adapter;

import android.content.Context;
import android.widget.TextView;

import com.mimile.dropdownmenu.R;

import java.util.List;


public class ListTextDropDownAdapter extends DropDownAdapter {

    public ListTextDropDownAdapter(Context context, List<String> list) {
        super(context,list);
    }

    @Override
    protected int inflateItemView() {
        return R.layout.item_list_text_drop_down;
    }

    @Override
    protected void actionNotSelect(TextView textView) {
        textView.setBackgroundResource(R.color.white);
    }

    @Override
    protected void actionSelect(TextView textView) {
        textView.setBackgroundResource(R.color.check_bg);
    }
}