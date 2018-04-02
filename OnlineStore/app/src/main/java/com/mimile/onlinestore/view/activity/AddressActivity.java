package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Address;
import com.mimile.onlinestore.util.AddressOptionCallBack;
import com.mimile.onlinestore.view.adapter.AddressAdapter;
import com.mimile.onlinestore.view.customview.AddressOptionDialog;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class AddressActivity extends CommonBaseActivity implements View.OnClickListener,AddressOptionCallBack{
    private TextView addAddress;
    private ListView addressListView;
    private AddressAdapter addressAdapter;
    private List<Address> list = new ArrayList<Address>();
    private AddressOptionDialog dialog;
    private TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addAddress = (TextView) findViewById(R.id.add_address);
        addressListView = (ListView) findViewById(R.id.address_list);
        back = (TextView) findViewById(R.id.back_tv);
        back.setOnClickListener(this);
        addAddress.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address:
                Intent intent = new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.back_tv:
                finish();
                break;
            default:
                break;
        }
    }

    private void initData() {
        Address address1 = new Address("夏晓培","13564673224","000000","成都市成华区","龙潭市永兴街","508号",0,"000001","A");
        Address address2 = new Address("夏晓培","13564673224","000000","成都市成华区","龙潭市永兴街","508号",1,"000001","B");
        Address address3 = new Address("夏晓培","13564673224","000000","成都市成华区","龙潭市永兴街","508号",0,"000001","C");
        list.add(address1);
        list.add(address2);
        list.add(address3);
        addressAdapter = new AddressAdapter(this,list);
        addressListView.setAdapter(addressAdapter);
        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = (Address)addressAdapter.getItem(position);
//                RadioButton rb = (RadioButton) view.findViewById(R.id.address_list_item_radiobtn);
                if (address.getActive() == 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i != position){
                            list.get(i).setActive(0);
                        }else {
                            list.get(i).setActive(1);
//                            rb.setChecked(true);
                        }
                    }
                    //TODO 改变数据库
                    addressAdapter.updateListView(list);
                }
                Toast.makeText(getApplication(),"opint"+ position,Toast.LENGTH_SHORT).show();
            }
        });
        addressListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new AddressOptionDialog(AddressActivity.this,AddressActivity.this,position);
                dialog.show();
                return true;
            }
        });
    }


    @Override
    public void modifyAddress(int position) {
        if (dialog != null)
            dialog.dismiss();
        Intent intent = new Intent(this,AddAddressActivity.class);
        startActivity(intent);
    }

    @Override
    public void deleteAddress(int position) {
        if (dialog != null)
            dialog.dismiss();
    }
}
