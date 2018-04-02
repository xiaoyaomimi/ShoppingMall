package com.mimile.onlinestore.view.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.mimile.base.view.dialog.SweetAlertDialog;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.activity.UserCommonActivity;


/**
 * Created by caidongdong on 2016/12/9 14:13
 * email : mircaidong@163.com
 * 分享app
 */
public class ConfirmPayDialog extends Dialog {
    private Context context;
    private ImageView cancel;
    private Button confirm;
    private Handler handler;

    public ConfirmPayDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_confirm_pay);
        cancel = (ImageView) findViewById(R.id.confirm_pay_popwin_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        confirm = (Button) findViewById(R.id.confirm_pay_popwin_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dismiss();
                final SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("正在加载");
                pDialog.setCancelable(false);
                pDialog.show();

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      pDialog.dismiss();
                        Intent intent = new Intent(context, UserCommonActivity.class);
                        intent.putExtra("ACTION_VALUE",12);
                        context.startActivity(intent);
                        ((Activity)context).finish();
                    }
                },2000);

            }
        });
        initWindow();

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
}
