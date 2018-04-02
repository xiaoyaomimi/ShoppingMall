package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mimile.base.util.PhoneFormatCheckUtils;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.OptionSelectedCallBack;
import com.mimile.onlinestore.view.customview.PhoneNumAreaSelectDialog;
import com.mimile.onlinestore.view.customview.VerificationCodeDialog;

/**
 * Created by caidongdong on 2016/12/7 14:50
 * email : mircaidong@163.com
 */
public class ModifyPhoneNumFragment extends ContentFragment implements View.OnClickListener,OptionSelectedCallBack{
    private RelativeLayout rl;
    private TextView area;
    private EditText newPhoneNum;
    private Button next;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_modify_phone_num,null);
        rl = (RelativeLayout) view.findViewById(R.id.fragment_modify_phone_num_area_rl);
        area = (TextView) view.findViewById(R.id.fragment_modify_phone_num_area_tv);
        newPhoneNum = (EditText) view.findViewById(R.id.fragment_modify_phone_num_area_editTv);
        next = (Button) view.findViewById(R.id.fragment_modify_phone_num_area_button);
        next.setOnClickListener(this);
        rl.setOnClickListener(this);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_modify_phone_num_area_rl:
                PhoneNumAreaSelectDialog dialog = new PhoneNumAreaSelectDialog(getActivity(),this);
                dialog.show();
                break;
            case R.id.fragment_modify_phone_num_area_button:
                String phoneNum = newPhoneNum.getText().toString();
                if (phoneNum != null && !phoneNum.isEmpty()) {
                    if (PhoneFormatCheckUtils.isPhoneLegal(phoneNum)){
                        VerificationCodeDialog vdialog = new VerificationCodeDialog(getActivity(),phoneNum);
                        vdialog.setCanceledOnTouchOutside(false);
                        vdialog.show();
                    }else {
                        Toast.makeText(getActivity(),"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(),"请输入新的手机号码",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void cancle() {

    }

    @Override
    public void confirm(String str) {
        if (str != null && !str.isEmpty())
            area.setText(str);
    }
}
