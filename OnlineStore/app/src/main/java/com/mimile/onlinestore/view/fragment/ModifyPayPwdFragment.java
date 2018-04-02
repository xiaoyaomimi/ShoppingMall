package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mimile.base.CommonUtil;
import com.mimile.base.view.dialog.SweetAlertDialog;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;

/**
 * Created by caidongdong on 2016/12/9 15:53
 * email : mircaidong@163.com
 */
public class ModifyPayPwdFragment extends ContentFragment implements View.OnClickListener{
    private EditText oldPayPwd;
    private EditText newPayPwd;
    private Button confirm;
    private CheckBox checkBox;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_modify_pay_pwd,null);
        oldPayPwd = (EditText) view.findViewById(R.id.fragment_modify_pay_pwd_old_pwd_et);
        newPayPwd = (EditText) view.findViewById(R.id.fragment_modify_pay_pwd_new_pwd_et);
        confirm = (Button) view.findViewById(R.id.fragment_modify_pay_pwd_confirm_button);
        checkBox = (CheckBox) view.findViewById(R.id.fragment_modify_pay_pwd_cb);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        confirm.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    oldPayPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    newPayPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    oldPayPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    newPayPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_modify_pay_pwd_confirm_button:
                String oldPwd = oldPayPwd.getText().toString();
                String newPwd = newPayPwd.getText().toString();
                if (oldPwd == null || oldPwd.isEmpty()){
                    Toast.makeText(getActivity(),"请输入当前支付密码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (newPwd == null || newPwd.isEmpty()){
                    Toast.makeText(getActivity(),"请输入新的支付密码",Toast.LENGTH_SHORT).show();
                    break;
                }else if (CommonUtil.checkPassword(newPwd)){
                    Toast.makeText(getActivity(),"新密码不符合要求",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (newPwd.equals(oldPwd)){
                    Toast.makeText(getActivity(),"新密码不能和当前支付密码一样",Toast.LENGTH_SHORT).show();
                    break;
                }
                final SweetAlertDialog dialog = new SweetAlertDialog(getActivity(),SweetAlertDialog.PROGRESS_TYPE);
                dialog.setTitleText("正在请求服务器");
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"修改支付密码成功",Toast.LENGTH_SHORT).show();
                        ((UserCommonActivity)getActivity()).getTitleText().setText(R.string.account_security);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                        dialog.dismiss();
                    }
                },2000);
                break;
            default:
                break;
        }
    }
}
