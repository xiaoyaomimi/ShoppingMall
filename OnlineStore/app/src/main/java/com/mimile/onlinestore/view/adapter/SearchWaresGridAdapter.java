package com.mimile.onlinestore.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Wares;

import java.util.List;

/**
 * Created by caidongdong on 2017/1/9 15:47
 * email : mircaidong@163.com
 */

public class SearchWaresGridAdapter extends BaseQuickAdapter<Wares> {

    public SearchWaresGridAdapter(List<Wares> data) {
        super(R.layout.list_item_search_wares_list_grid,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Wares wares) {

    }
}
