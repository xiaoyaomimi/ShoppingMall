package com.mimile.onlinestore.view.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mimile.base.view.popwin.BaseDialog;
import com.mimile.base.view.tagview.FlowLayout;
import com.mimile.base.view.tagview.TagAdapter;
import com.mimile.base.view.tagview.TagFlowLayout;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/27 10:53
 * email : mircaidong@163.com
 */

public class WaresOptDialog extends BaseDialog implements View.OnClickListener{
    private ImageView close;
    private TextView add;
    private TextView reduce;
    private List<String> list;
    private TagFlowLayout flowLayout;
    private TextView confirm;

    public WaresOptDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwin_wares_opt_select);
        add = (TextView) findViewById(R.id.popwin_wares_opt_select_add_tv);
        reduce = (TextView) findViewById(R.id.popwin_wares_opt_select_reduce_tv);
        confirm = (TextView) findViewById(R.id.popwin_wares_opt_confirm_tv);
        close = (ImageView) findViewById(R.id.popwin_wares_opt_close);
        flowLayout = (TagFlowLayout) findViewById(R.id.popwin_wares_opt_select_fl);
        flowLayout.setMaxSelectCount(1);
        final LayoutInflater mInflater = LayoutInflater.from(context);
        initData();
        flowLayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) mInflater.inflate(R.layout.popwin_wares_opt_item,
                        flowLayout, false);
                tv.setText(str);
                return tv;
            }
        });
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        close.setOnClickListener(this);
        confirm.setOnClickListener(this);


    }

    private void initData() {
        list = new ArrayList<>();
        list.add("可实现交");
        list.add("可实现交错式网格");
        list.add("可实现交错格");
        list.add("可实现交格");
        list.add("可实现交错式网格");
        list.add("可实式网格");
        list.add("可网格");
        list.add("可实错式网格");
        list.add("可实错式网格");
        list.add("可错式网格");
        list.add("可实式网格");
        list.add("可实错式网格");
        list.add("可实现交式网格");
        list.add("可实现交错式网格");
        list.add("可实现交错式网格");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popwin_wares_opt_select_add_tv:

                break;
            case R.id.popwin_wares_opt_select_reduce_tv:

                break;
            case R.id.popwin_wares_opt_close:
                dismiss();
                break;
            case R.id.popwin_wares_opt_confirm_tv:
                Intent intent = new Intent(context, UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",11);
                context.startActivity(intent);
                dismiss();
                break;
            default:
                break;
        }
    }
}
