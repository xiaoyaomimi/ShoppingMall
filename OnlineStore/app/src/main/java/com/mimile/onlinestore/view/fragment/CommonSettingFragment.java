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
public class CommonSettingFragment extends ContentFragment implements View.OnClickListener{
    private RelativeLayout saveFlowRl;
    private RelativeLayout locationRl;
    private RelativeLayout shakeRl;
    private RelativeLayout cleanRl;
    private CheckBox saveFlowCb;
    private CheckBox locationCb;
    private CheckBox shakeCb;
    private BaseSharedPreference sharedPreference;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_common_setting,null);
        saveFlowRl = (RelativeLayout) view.findViewById(R.id.fragment_common_setting_save_flow_mode_rl);
        locationRl = (RelativeLayout) view.findViewById(R.id.fragment_common_setting_location_mode_rl);
        shakeRl = (RelativeLayout) view.findViewById(R.id.fragment_common_setting_shake_mode_rl);
        cleanRl = (RelativeLayout) view.findViewById(R.id.fragment_common_setting_clean_rl);
        saveFlowCb = (CheckBox) view.findViewById(R.id.fragment_common_setting_save_flow_mode_cb);
        locationCb = (CheckBox) view.findViewById(R.id.fragment_common_setting_location_mode_cb);
        shakeCb = (CheckBox) view.findViewById(R.id.fragment_common_setting_shake_mode_cb);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        saveFlowRl.setOnClickListener(this);
        locationRl.setOnClickListener(this);
        shakeRl.setOnClickListener(this);
        cleanRl.setOnClickListener(this);
        saveFlowCb.setOnClickListener(this);
        locationCb.setOnClickListener(this);
        shakeCb.setOnClickListener(this);
        sharedPreference = new BaseSharedPreference(getActivity());
        saveFlowCb.setChecked(sharedPreference.getBoolean("save_flow_mode"));
        locationCb.setChecked(sharedPreference.getBoolean("location_mode"));
        shakeCb.setChecked(sharedPreference.getBoolean("shake_mode"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_common_setting_save_flow_mode_rl:
                changeCheckBoxStatus(saveFlowCb);
                sharedPreference.setBoolean("save_flow_mode",saveFlowCb.isChecked());
                break;
            case R.id.fragment_common_setting_location_mode_rl:
                changeCheckBoxStatus(locationCb);
                sharedPreference.setBoolean("location_mode",locationCb.isChecked());
                break;
            case R.id.fragment_common_setting_shake_mode_rl:
                changeCheckBoxStatus(shakeCb);
                sharedPreference.setBoolean("shake_mode",shakeCb.isChecked());
                break;
            case R.id.fragment_common_setting_clean_rl:
                //TODO 清理缓存数据

                break;
            case R.id.fragment_common_setting_save_flow_mode_cb:
                sharedPreference.setBoolean("save_flow_mode",saveFlowCb.isChecked());
                break;
            case R.id.fragment_common_setting_location_mode_cb:
                sharedPreference.setBoolean("location_mode",locationCb.isChecked());
                break;
            case R.id.fragment_common_setting_shake_mode_cb:
                sharedPreference.setBoolean("shake_mode",shakeCb.isChecked());
                break;
            default:
                break;
        }
    }

    private void changeCheckBoxStatus(CheckBox cb) {
        cb.setChecked(!cb.isChecked());
    }
}
