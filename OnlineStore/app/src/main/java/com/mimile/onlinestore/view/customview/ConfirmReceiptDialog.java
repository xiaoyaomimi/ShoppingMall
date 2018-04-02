package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mimile.base.view.dialog.SweetAlertDialog;
import com.mimile.base.view.keboard.PasswordKeyboardView;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;
import com.mimile.onlinestore.view.fragment.TradeSuccessFragment;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/22 15:18
 * email : mircaidong@163.com
 */

public class ConfirmReceiptDialog extends Dialog implements View.OnClickListener,PasswordKeyboardView.IOnKeyboardListener{
    private Context context;
    private String orderNum;
    private ImageView cancel;
    private TextView forgetPwd;
    private List<String> valueList;
    private PasswordKeyboardView keyboardView;
    private TextView pwd1;
    private TextView pwd2;
    private TextView pwd3;
    private TextView pwd4;
    private TextView pwd5;
    private TextView pwd6;
    private int position = 0;

    public ConfirmReceiptDialog(Context context, String orderNum) {
        super(context);
        this.orderNum = orderNum;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_confirm_receipt);
        setCanceledOnTouchOutside(false);
        forgetPwd = (TextView) findViewById(R.id.popwin_confirm_receipt_tv_forget);
        cancel = (ImageView) findViewById(R.id.confirm_receipt_popwin_cancel);
        keyboardView = (PasswordKeyboardView) findViewById(R.id.popwin_confirm_receipt_keyboardv);
        pwd1 = (TextView) findViewById(R.id.tv_pass1);
        pwd2 = (TextView) findViewById(R.id.tv_pass2);
        pwd3 = (TextView) findViewById(R.id.tv_pass3);
        pwd4 = (TextView) findViewById(R.id.tv_pass4);
        pwd5 = (TextView) findViewById(R.id.tv_pass5);
        pwd6 = (TextView) findViewById(R.id.tv_pass6);
        initWindow();
        keyboardView.setIOnKeyboardListener(this);
        cancel.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_receipt_popwin_cancel:
                dismiss();
                break;
            case R.id.popwin_confirm_receipt_tv_forget:

                break;
            default:
                break;
        }
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
//        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);
    }

    @Override
    public void onInsertKeyEvent(String text) {
        switch (position) {
            case 0:
                pwd1.setText(text);
                break;
            case 1:
                pwd2.setText(text);
                break;
            case 2:
                pwd3.setText(text);
                break;
            case 3:
                pwd4.setText(text);
                break;
            case 4:
                pwd5.setText(text);
                break;
            case 5:
                pwd6.setText(text);
                break;
            default:
                break;
        }
        if (position < 6) {
            position++;
        }
        if (position == 6)
            confirmReceipt();
    }

    @Override
    public void onDeleteKeyEvent() {
        switch (position) {
            case 0:
                pwd1.setText("");
                break;
            case 1:
                pwd2.setText("");
                break;
            case 2:
                pwd3.setText("");
                break;
            case 3:
                pwd4.setText("");
                break;
            case 4:
                pwd5.setText("");
                break;
            case 5:
                pwd6.setText("");
                break;
            default:
                break;
        }
        if (position > 0)
            position --;
    }

    private void confirmReceipt() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("正在加载");
        pDialog.setCancelable(false);
        pDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
                dismiss();
                UserCommonActivity activity = (UserCommonActivity)context;
                activity.getTitleText().setText(activity.getString(R.string.trade_success));
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.user_common_activity_fl,new TradeSuccessFragment()).commit();
            }
        },2000);
    }
}
