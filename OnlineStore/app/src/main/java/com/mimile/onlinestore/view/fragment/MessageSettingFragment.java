package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.mimile.base.util.BaseSharedPreference;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;

/**
 * Created by caidongdong on 2016/12/7 14:50
 * email : mircaidong@163.com
 */
public class MessageSettingFragment extends ContentFragment implements View.OnClickListener{
    private RelativeLayout msgAlertRl;
    private RelativeLayout msgRingAlertRl;
    private RelativeLayout msgVibrateAlertRl;
    private CheckBox msgAlertCb;
    private CheckBox msgRingAlertCb;
    private CheckBox msgVibrateAlertCb;
    private BaseSharedPreference sharedPreference;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_message_setting,null);
        msgAlertRl = (RelativeLayout) view.findViewById(R.id.fragment_message_setting_msg_alert_rl);
        msgRingAlertRl = (RelativeLayout) view.findViewById(R.id.fragment_message_setting_msg_ring_alert_rl);
        msgVibrateAlertRl = (RelativeLayout) view.findViewById(R.id.fragment_message_setting_msg_vibrate_alert_rl);
        msgAlertCb = (CheckBox) view.findViewById(R.id.fragment_message_setting_msg_alert_cb);
        msgRingAlertCb = (CheckBox) view.findViewById(R.id.fragment_message_setting_msg_ring_alert_cb);
        msgVibrateAlertCb = (CheckBox) view.findViewById(R.id.fragment_message_setting_msg_vibrate_alert_cb);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        msgAlertRl.setOnClickListener(this);
        msgRingAlertRl.setOnClickListener(this);
        msgVibrateAlertRl.setOnClickListener(this);
        msgAlertCb.setOnClickListener(this);
        msgRingAlertCb.setOnClickListener(this);
        msgVibrateAlertCb.setOnClickListener(this);
        sharedPreference = new BaseSharedPreference(getActivity());
        msgAlertCb.setChecked(sharedPreference.getBoolean("msg_alert"));
        msgRingAlertCb.setChecked(sharedPreference.getBoolean("msg_ring_alert"));
        msgVibrateAlertCb.setChecked(sharedPreference.getBoolean("msg_vibrate_alert"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_message_setting_msg_alert_rl:
                chekckBoxStatus(msgAlertCb);
                sharedPreference.setBoolean("msg_alert",msgAlertCb.isChecked());
                checkBoxCancelAlert();
                break;
            case R.id.fragment_message_setting_msg_ring_alert_rl:
                chekckBoxStatus(msgRingAlertCb);
                sharedPreference.setBoolean("msg_ring_alert",msgRingAlertCb.isChecked());
                checkBoxMsgAlert();
                break;
            case R.id.fragment_message_setting_msg_vibrate_alert_rl:
                chekckBoxStatus(msgVibrateAlertCb);
                sharedPreference.setBoolean("msg_vibrate_alert",msgVibrateAlertCb.isChecked());
                checkBoxMsgAlert();
                break;
            case R.id.fragment_message_setting_msg_alert_cb:
                sharedPreference.setBoolean("msg_alert",msgAlertCb.isChecked());
                checkBoxCancelAlert();
                break;
            case R.id.fragment_message_setting_msg_ring_alert_cb:
                sharedPreference.setBoolean("msg_ring_alert",msgRingAlertCb.isChecked());
                checkBoxMsgAlert();
                break;
            case R.id.fragment_message_setting_msg_vibrate_alert_cb:
                sharedPreference.setBoolean("msg_vibrate_alert",msgVibrateAlertCb.isChecked());
                checkBoxMsgAlert();
                break;
            default:
                break;
        }
    }

    private void chekckBoxStatus(CheckBox cb) {
        if (cb.isChecked()) {
            cb.setChecked(false);
        }else {
            cb.setChecked(true);
        }
    }

    /**
     * 勾选开启消息提示
     */
    private void checkBoxMsgAlert() {
        if (!msgAlertCb.isChecked()) {
            msgAlertCb.setChecked(true);
            sharedPreference.setBoolean("msg_alert",true);
        }
    }

    /**
     * 取消震动和声音
     */
    public void checkBoxCancelAlert() {
        if (!msgAlertCb.isChecked()){
            sharedPreference.setBoolean("msg_ring_alert",false);
            sharedPreference.setBoolean("msg_vibrate_alert",false);
            msgRingAlertCb.setChecked(false);
            msgVibrateAlertCb.setChecked(false);
        }
    }
}
