package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.customview.AppUpdateDialog;
import com.mimile.onlinestore.view.customview.ShareAppDialog;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class UserInfoActivity extends CommonBaseActivity implements View.OnClickListener{
    private RelativeLayout userSetting;
    private RelativeLayout userSecurity;
    private RelativeLayout userMessageSetting;
    private RelativeLayout userCommonSetting;
    private RelativeLayout userUpdateCheck;
    private RelativeLayout userShare;
    private RelativeLayout aboutUs;
    private AppUpdateDialog dialog;
    private ShareAppDialog shareAppDialog;
    private TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userSetting = (RelativeLayout) findViewById(R.id.user_info_rl_setting);
        userSecurity = (RelativeLayout) findViewById(R.id.user_info_rl_account_security);
        userMessageSetting = (RelativeLayout) findViewById(R.id.user_info_rl_message_setting);
        userCommonSetting = (RelativeLayout) findViewById(R.id.user_info_rl_common_setting);
        userUpdateCheck = (RelativeLayout) findViewById(R.id.user_info_rl_update_check);
        userShare = (RelativeLayout) findViewById(R.id.user_info_rl_share);
        aboutUs = (RelativeLayout) findViewById(R.id.user_info_rl_about_us);
        titleText = (TextView) findViewById(R.id.activity_userinfo_title_tv);
        userSecurity.setOnClickListener(this);
        userSetting.setOnClickListener(this);
        userMessageSetting.setOnClickListener(this);
        userCommonSetting.setOnClickListener(this);
        userUpdateCheck.setOnClickListener(this);
        userShare.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        titleText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.user_info_rl_setting:
                intent = new Intent(this,UserSettingActivity.class);
                break;
            case R.id.user_info_rl_account_security:
                intent = new Intent(this,UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",1);
                break;
            case R.id.user_info_rl_message_setting:
                intent = new Intent(this,UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",2);
                break;
            case R.id.user_info_rl_common_setting:
                intent = new Intent(this,UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",4);
                break;
            case R.id.user_info_rl_update_check:
                dialog = new AppUpdateDialog(this,"6.0","西部世界第一季全集,西部世界第一季在线观看和西部世界第一季迅雷下载由奇特影院收集于互联网,美国电视剧西部世界","http://gdown.baidu.com/data/wisegame/6679f51ef2388bd5/xiaomishangcheng_20161110.apk");
                dialog.show();
                break;
            case R.id.user_info_rl_share:
                shareAppDialog = new ShareAppDialog(this);
                shareAppDialog.show();
                break;
            case R.id.user_info_rl_about_us:
                intent = new Intent(this,UserCommonActivity.class);
                intent.putExtra("ACTION_VALUE",5);
                break;
            case R.id.activity_userinfo_title_tv:
                finish();
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
