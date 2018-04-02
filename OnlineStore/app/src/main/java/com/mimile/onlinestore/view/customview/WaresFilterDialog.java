package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mimile.base.view.tagview.FlowLayout;
import com.mimile.base.view.tagview.TagAdapter;
import com.mimile.base.view.tagview.TagFlowLayout;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2017/1/9 16:27
 * email : mircaidong@163.com
 */

public class WaresFilterDialog extends Dialog implements View.OnClickListener{
    protected Context context;
    private TagFlowLayout brandTagFl;
    private TagFlowLayout discountTagFl;
    private List<String> list;
    private List<String> discount;
    private RelativeLayout brandRl;
    private CheckBox  brandCb;
    private TextView reset;
    private TextView confirm;

    public WaresFilterDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        initWindow();
        setContentView(R.layout.popwin_wares_filter);
        getWindow().setWindowAnimations(R.style.dialogWindowAnimRightIn);
        brandTagFl = (TagFlowLayout) findViewById(R.id.popwin_wares_filter_brand_fl);
        discountTagFl = (TagFlowLayout) findViewById(R.id.popwin_wares_filter_discount_fl);
        brandRl = (RelativeLayout) findViewById(R.id.popwin_wares_filter_brand_expand_rl);
        brandCb = (CheckBox) findViewById(R.id.popwin_wares_filter_brand_expand_cb);
        reset = (TextView) findViewById(R.id.popwin_wares_filter_reset_tv);
        confirm = (TextView) findViewById(R.id.popwin_wares_filter_confirm_tv);
        brandTagFl.setMaxSelectCount(1);
        list = new ArrayList<>();
        list.add("南极人");
        list.add("妆尼");
        list.add("Beijirong/北极绒");
        list.add("3M");
        list.add("宜家");
        list.add("lovo");
        list.add("南极人");
        list.add("妆尼");
        list.add("Beijirong/北极绒");
        list.add("3M");
        list.add("宜家");
        list.add("lovo");
        discount = new ArrayList<>();
        discount.add("天猫");
        discount.add("包邮");
        discount.add("消费者保障");
        discount.add("全球购");
        discount.add("天猫国际");
        discount.add("淘金币抵钱");
        discount.add("7+天内退货");
        discount.add("货到付款");
        discount.add("天猫超市");
        discount.add("通用排序");
        TagAdapter tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.wares_filter_dialog_item,
                        brandTagFl, false);
                tv.setText(str);
                return tv;
            }
        };
        brandTagFl.setAdapter(tagAdapter);
        TagAdapter discountAdapter = new TagAdapter<String>(discount) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.wares_filter_dialog_item,
                        discountTagFl, false);
                tv.setText(str);
                return tv;
            }
        };
        discountTagFl.setAdapter(discountAdapter);
        brandRl.setOnClickListener(this);
        brandCb.setOnClickListener(this);
        reset.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.RIGHT;
        dialogWindow.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popwin_wares_filter_brand_expand_rl:
                changeTagLayoutParas(brandTagFl,brandCb,brandCb.isChecked());
                break;
            case R.id.popwin_wares_filter_brand_expand_cb:
                changeTagLayoutParas(brandTagFl,brandCb,!brandCb.isChecked());
                break;
            case R.id.popwin_wares_filter_reset_tv:

                break;
            case R.id.popwin_wares_filter_confirm_tv:
                dismiss();
                break;
            default:
                break;
        }
    }

    private void changeTagLayoutParas(TagFlowLayout tagFlowLayout,CheckBox checkBox,boolean isExpand) {
        if (isExpand) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(context,100));
            tagFlowLayout.setLayoutParams(params);
            checkBox.setChecked(false);
        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tagFlowLayout.setLayoutParams(params);
            checkBox.setChecked(true);
        }
    }
}
