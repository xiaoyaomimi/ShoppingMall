package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mimile.base.view.activity.BaseActivity;
import com.mimile.base.view.zxing.android.CaptureActivity;
import com.mimile.onlinestore.R;

public class QRDroidActivity extends BaseActivity implements View.OnClickListener{
    private Button button;
    private TextView textView;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    @Override
    public void initView() {
        setContentView(R.layout.activity_qrdroid);
        button = (Button) findViewById(R.id.qr_droid);
        textView = (TextView) findViewById(R.id.qr_result);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qr_droid:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SCAN);
                break;
            default:
                break;
        }
    }

}
