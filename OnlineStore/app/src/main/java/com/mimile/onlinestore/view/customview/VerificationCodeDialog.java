package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;
import com.mimile.onlinestore.view.fragment.AccountSecurityFragment;

/**
 * Created by caidongdong on 2016/12/30 12:43
 * email : mircaidong@163.com
 */

public class VerificationCodeDialog extends Dialog implements View.OnClickListener{
    private Context context;
    private ImageView close;
    private TextView note;
    private TextView sendAgain;
    private EditText verificationCode;
    private Button confirm;
    private Button ok;
    private String str;
    private LinearLayout normalLL;
    private LinearLayout successLL;
    private  CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            sendAgain.setText(millisUntilFinished/1000 + "秒");
        }

        @Override
        public void onFinish() {
            sendAgain.setEnabled(true);
            sendAgain.setText("重新发送");
        }
    };

    public VerificationCodeDialog(Context context,String str) {
        super(context);
        this.str = str;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_check_verification_code);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        close = (ImageView) findViewById(R.id.popwin_verification_code_close_img);
        note = (TextView) findViewById(R.id.popwin_verification_code_note_tv);
        verificationCode = (EditText) findViewById(R.id.popwin_verification_code_edit_text);
        confirm = (Button) findViewById(R.id.popwin_verification_code_confirm_btn);
        ok = (Button) findViewById(R.id.popwin_verification_code_ok_btn);
        sendAgain = (TextView) findViewById(R.id.popwin_verification_code_send_again_tv);
        normalLL = (LinearLayout) findViewById(R.id.popwin_verification_code_normal_ll);
        successLL = (LinearLayout) findViewById(R.id.popwin_verification_code_success_rl);
        note.setText("输入手机号"+ str +"收到的短信验证码");
        close.setOnClickListener(this);
        confirm.setOnClickListener(this);
        sendAgain.setOnClickListener(this);
        ok.setOnClickListener(this);
//        getWindow().setWindowAnimations(R.style.dialogWindowAnimRightIn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popwin_verification_code_close_img:
                countDownTimer.cancel();
                dismiss();
                break;
            case R.id.popwin_verification_code_confirm_btn:
                String code = verificationCode.getText().toString();
                if (code.length() == 6) {
                    //TODO 请求网络验证
                    hide();
                    confirm.setText("正在验证");
                    confirm.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            confirm.setText("确定");
                            normalLL.setVisibility(View.INVISIBLE);
                            successLL.setVisibility(View.VISIBLE);
                        }
                    },1000);
                }else {
                    Toast.makeText(context,"您输入的验证码位数不正确",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.popwin_verification_code_send_again_tv:
                sendAgain.setEnabled(false);
                countDownTimer.start();
                break;
            case R.id.popwin_verification_code_ok_btn:
                countDownTimer.cancel();
                dismiss();
                ((UserCommonActivity)context).getTitleText().setText(R.string.account_security);
                ((UserCommonActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new AccountSecurityFragment()).commit();
                break;
            default:
                break;
        }
    }

    public void hide() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
}
