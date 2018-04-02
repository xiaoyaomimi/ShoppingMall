package com.mimile.onlinestore.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.fragment.AboutFragment;
import com.mimile.onlinestore.view.fragment.AccountSecurityFragment;
import com.mimile.onlinestore.view.fragment.CommonSettingFragment;
import com.mimile.onlinestore.view.fragment.ConfirmOrderFragment;
import com.mimile.onlinestore.view.fragment.MessageSettingFragment;
import com.mimile.onlinestore.view.fragment.OrderFragment;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class UserCommonActivity extends FragmentActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private TextView titleText;
    private int action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.parseColor("#FF1493"));
        }
        setContentView(R.layout.activity_user_common);
        titleText = (TextView) findViewById(R.id.user_common_activity_tv);
        titleText.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        action = getIntent().getIntExtra("ACTION_VALUE",0);
        switch (action) {
            case 1:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                titleText.setText(R.string.account_security);
                break;
            case 2:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new MessageSettingFragment()).commit();
                titleText.setText(R.string.message_alert);
                break;
            case 3:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                titleText.setText(R.string.member_center);
                break;
            case 4:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new CommonSettingFragment()).commit();
                titleText.setText(R.string.common);
                break;
            case 5:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new AboutFragment()).commit();
                titleText.setText(R.string.about_us);
                break;
            case 6:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                titleText.setText(R.string.share_friends);
                break;
            case 11:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new ConfirmOrderFragment()).commit();
                titleText.setText(R.string.confirm_order);
                break;
            case 12:
                fragmentManager.beginTransaction().add(R.id.user_common_activity_fl,new OrderFragment()).commit();
                titleText.setText(R.string.my_order);
                break;
            default:
                break;
        }
    }

    public TextView getTitleText() {
        return titleText;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
