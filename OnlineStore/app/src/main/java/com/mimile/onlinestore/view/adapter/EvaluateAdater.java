package com.mimile.onlinestore.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Evaluate;

import java.util.List;

/**
 * Created by caidongdong on 2017/1/4 09:35
 * email : mircaidong@163.com
 */

public class EvaluateAdater extends BaseQuickAdapter<Evaluate> {

    public EvaluateAdater(List<Evaluate> data) {
        super(R.layout.list_item_evaluate,data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Evaluate evaluate) {

    }
}
