package com.mimile.onlinestore.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.LoginActivity;
import com.mimile.onlinestore.view.activity.LogisticsActivity;
import com.mimile.onlinestore.view.activity.UserCommonActivity;
import com.mimile.onlinestore.view.activity.UserInfoActivity;
import com.mimile.onlinestore.view.customview.RoundImageView;

/**
 * Created by caidongdong on 2016/11/28 16:01
 * email : mircaidong@163.com
 */
public class MeFragment extends ContentFragment implements View.OnClickListener{
    private TextView unPaid;
    private TextView unDelivered;
    private TextView receiptOrder;
    private TextView receiptEvaluate;
    private TextView setting;
    private RoundImageView headImg;
    private RelativeLayout orderRl;
    private RelativeLayout myWalletRl;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_me, null);
        unPaid = (TextView) view.findViewById(R.id.tv_unpaid);
        unDelivered = (TextView) view.findViewById(R.id.undelivered_order);
        receiptOrder = (TextView) view.findViewById(R.id.receipt_order);
        receiptEvaluate = (TextView) view.findViewById(R.id.receipt_evaluate);
        setting = (TextView) view.findViewById(R.id.me_tv_setting);
        headImg = (RoundImageView) view.findViewById(R.id.headerview);
        orderRl = (RelativeLayout) view.findViewById(R.id.fragment_me_order_rl);
        myWalletRl = (RelativeLayout) view.findViewById(R.id.fragment_me_mywallet_rl);

        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        setting.setOnClickListener(this);
        headImg.setOnClickListener(this);
        orderRl.setOnClickListener(this);
        myWalletRl.setOnClickListener(this);
        unPaid.setOnClickListener(this);
        unDelivered.setOnClickListener(this);
        receiptOrder.setOnClickListener(this);
        receiptEvaluate.setOnClickListener(this);
        setBadgeCount(unPaid,2);
    }


    /**
     * 设置badgeview右上角的数字
     * @param view
     * @param count
     */
    private void setBadgeCount(View view,int count){
        BadgeView badgeView = new BadgeView(getActivity());
        badgeView.setTargetView(view);
        badgeView.setBadgeMargin(0,0,15,0);
        badgeView.setBadgeCount(count);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.me_tv_setting:
                intent = new Intent(getActivity(), UserInfoActivity.class);
                break;
            case R.id.headerview:
                intent = new Intent(getActivity(), LoginActivity.class);
                break;
            case R.id.fragment_me_order_rl:
                intent = new Intent(getActivity(), UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",12);
                break;
            case R.id.fragment_me_mywallet_rl:
                intent = new Intent(getActivity(), LogisticsActivity.class);
                break;
            case R.id.tv_unpaid:
                intent = preparePara(1);
                break;
            case R.id.undelivered_order:
                intent = preparePara(2);
                break;
            case R.id.receipt_order:
                intent = preparePara(3);
                break;
            case R.id.receipt_evaluate:
                intent = preparePara(4);
                break;
            default:
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    private Intent preparePara(int position) {
        Intent intent = null;
        intent = new Intent(getActivity(), UserCommonActivity.class);
        intent.putExtra("ACTION_VALUE",12);
        intent.putExtra("DEFALUAT_POSITION",position);
        return intent;
    }
}
