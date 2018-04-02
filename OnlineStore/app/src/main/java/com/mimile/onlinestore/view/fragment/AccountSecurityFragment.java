package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;

/**
 * Created by caidongdong on 2016/12/7 14:50
 * email : mircaidong@163.com
 */
public class AccountSecurityFragment extends ContentFragment implements View.OnClickListener{
    private RelativeLayout modifyPhoneNum;
    private RelativeLayout modifyLoginPwd;
    private RelativeLayout modifyPayPwd;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_account_security,null);
        modifyPhoneNum = (RelativeLayout) view.findViewById(R.id.fragment_account_security_modify_phone_num);
        modifyLoginPwd = (RelativeLayout) view.findViewById(R.id.fragment_account_security_modify_login_pwd);
        modifyPayPwd = (RelativeLayout) view.findViewById(R.id.fragment_account_security_modify_pay_pwd);
        modifyPhoneNum.setOnClickListener(this);
        modifyLoginPwd.setOnClickListener(this);
        modifyPayPwd.setOnClickListener(this);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_account_security_modify_phone_num:
                ((UserCommonActivity)getActivity()).getTitleText().setText(R.string.modify_phone_num);
                getFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new ModifyPhoneNumFragment()).commit();
                break;
            case R.id.fragment_account_security_modify_login_pwd:
                ((UserCommonActivity)getActivity()).getTitleText().setText(R.string.modify_pwd);
                getFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new ModifyLoginPwdFragment()).commit();
                break;
            case R.id.fragment_account_security_modify_pay_pwd:
                ((UserCommonActivity)getActivity()).getTitleText().setText(R.string.modify_pay_pwd);
                getFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new ModifyPayPwdFragment()).commit();
                break;
            default:
                break;
        }
    }
}
