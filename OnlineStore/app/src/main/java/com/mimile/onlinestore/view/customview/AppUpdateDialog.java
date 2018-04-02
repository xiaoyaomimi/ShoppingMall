package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.api.AppUpdateService;

/**
 * Created by caidongdong on 2016/12/9 11:08
 * email : mircaidong@163.com
 * app提示更新界面
 */
public class AppUpdateDialog extends Dialog implements View.OnClickListener{
    private String version;
    private String detial;
    private String apkUrl;
    private LinearLayout cancel;
    private LinearLayout confirm;
    private TextView textViewversion;
    private TextView textViewdetial;
    private Context context;


    public AppUpdateDialog(Context context, String version, String detial, String apkUrl) {
        super(context);
        this.version = version;
        this.detial = detial;
        this.context = context;
        this.apkUrl = apkUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.app_update_dialog);
        cancel = (LinearLayout) findViewById(R.id.dialog_ll_update_cancel);
        confirm = (LinearLayout) findViewById(R.id.dialog_ll_update_confirm);
        textViewversion = (TextView) findViewById(R.id.dialog_tv_app_version_note);
        textViewdetial = (TextView) findViewById(R.id.dialog_tv_update_detial);
        setCancelable(true);
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        textViewversion.setText((context.getResources().getString(R.string.app_update_notice)).replace("version",version));
        textViewdetial.setText(detial);
        textViewdetial.setMovementMethod(new ScrollingMovementMethod());
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_ll_update_cancel:
                dismiss();
                break;
            case R.id.dialog_ll_update_confirm:
                Intent intent = new Intent(context,AppUpdateService.class);
                intent.putExtra("Key_App_Name","米米乐商城");
                intent.putExtra("Key_Down_Url",apkUrl);
                context.startService(intent);
                dismiss();
                break;
        }
    }
}
