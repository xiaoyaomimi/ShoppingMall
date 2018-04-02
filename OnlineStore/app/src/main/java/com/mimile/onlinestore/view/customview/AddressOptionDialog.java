package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.AddressOptionCallBack;

/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class AddressOptionDialog extends Dialog implements View.OnClickListener{
    private Context context;
    private RelativeLayout modify;
    private RelativeLayout delete;
    private AddressOptionCallBack callBack;
    private int position;

    public AddressOptionDialog(Context context, AddressOptionCallBack callBack,int position) {
        super(context);
        this.context = context;
        this.callBack = callBack;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_address_option_menu);
        modify = (RelativeLayout) findViewById(R.id.modify_address);
        delete = (RelativeLayout) findViewById(R.id.delete_address);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_address:
                Toast.makeText(context,"编辑",Toast.LENGTH_SHORT).show();
                callBack.modifyAddress(position);
                break;
            case R.id.delete_address:
                Toast.makeText(context,"删除",Toast.LENGTH_SHORT).show();
                callBack.deleteAddress(position);
                break;
            default:
                break;
        }
    }
}
