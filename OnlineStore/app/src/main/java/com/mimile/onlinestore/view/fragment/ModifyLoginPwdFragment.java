package com.mimile.onlinestore.view.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mimile.base.CommonUtil;
import com.mimile.base.view.dialog.SweetAlertDialog;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;

/**
 * Created by caidongdong on 2016/12/7 14:50
 * email : mircaidong@163.com
 */
public class ModifyLoginPwdFragment extends ContentFragment implements View.OnClickListener{
    private TextView timeCount;
    private CountDownTimer countDownTimer;
    private Button confirm;
    private EditText newPwd;
    private EditText newPwdAagin;
    private EditText verificationCode;
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_modify_pwd,null);
        timeCount = (TextView) view.findViewById(R.id.fragment_modify_pwd_send_again_tv);
        confirm = (Button) view.findViewById(R.id.fragment_modify_pwd_next_button);
        newPwd = (EditText) view.findViewById(R.id.fragment_modify_pwd_new_pwd_et);
        newPwdAagin = (EditText) view.findViewById(R.id.fragment_modify_pwd_new_pwd_again_et);
        verificationCode = (EditText) view.findViewById(R.id.fragment_modify_pwd_verification_editTv);
        timeCount.setText(R.string.get_ver_code);
        confirm.setOnClickListener(this);
        timeCount.setOnClickListener(this);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {

        countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeCount.setText(millisUntilFinished/1000 + getActivity().getResources().getString(R.string.get_ver_code_again_count));
            }

            @Override
            public void onFinish() {
                timeCount.setEnabled(true);
                timeCount.setText(R.string.get_ver_code_again);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_modify_pwd_send_again_tv:
                countDownTimer.start();
                break;
            case R.id.fragment_modify_pwd_next_button:
                String pwd = newPwd.getText().toString().trim();
                String pwdAgain = newPwdAagin.getText().toString().trim();
                String verCode = verificationCode.getText().toString().trim();
                if (pwd == null || pwd.isEmpty()){
                    Toast.makeText(getActivity(),"请输入新密码",Toast.LENGTH_SHORT).show();
                    break;
                }else if (!CommonUtil.checkPassword(pwd)){
                    Toast.makeText(getActivity(),"请按要求设置密码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (pwdAgain == null || pwdAgain.isEmpty()){
                    Toast.makeText(getActivity(),"请再次输入新密码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!pwd.equals(pwdAgain)){
                    Toast.makeText(getActivity(),"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    break;
                }
                if (verCode == null || verCode.isEmpty())
                {
                    Toast.makeText(getActivity(),"请输入验证码",Toast.LENGTH_SHORT).show();
                    break;
                }else if (verCode.length() < 6) {
                    Toast.makeText(getActivity(),"验证码位数不正确",Toast.LENGTH_SHORT).show();
                    break;
                }
                final SweetAlertDialog dialog = new SweetAlertDialog(getActivity());
                dialog.setTitleText("正在请求服务器");
                dialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"修改密码成功",Toast.LENGTH_SHORT).show();
                        ((UserCommonActivity)getActivity()).getTitleText().setText(R.string.account_security);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                        dialog.dismiss();
                    }
                },2000);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
