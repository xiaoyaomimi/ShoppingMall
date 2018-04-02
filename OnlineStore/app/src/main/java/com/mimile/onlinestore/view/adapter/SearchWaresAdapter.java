package com.mimile.onlinestore.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Wares;

import java.util.List;

/**
 * Created by caidongdong on 2017/1/6 14:45
 * email : mircaidong@163.com
 */

public class SearchWaresAdapter extends BaseQuickAdapter<Wares> {

    public SearchWaresAdapter(List<Wares> data) {
        super(R.layout.list_item_search_wares_list,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Wares wares) {

    }
}
