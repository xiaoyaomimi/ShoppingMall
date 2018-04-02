package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.OptionSelectedCallBack;
import com.mimile.pickerview.wheel.widget.OnWheelChangedListener;
import com.mimile.pickerview.wheel.widget.OnWheelScrollListener;
import com.mimile.pickerview.wheel.widget.WheelView;
import com.mimile.pickerview.wheel.widget.adapters.AbstractWheelTextAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/6 17:36
 * email : mircaidong@163.com
 */
public class PickerViewDialog extends Dialog implements View.OnClickListener,OnWheelChangedListener{
    private Context context;
    private ArrayList<String> provinceList;
    private List<ArrayList<String>> cityList;
    private List<List<ArrayList<String>>> districtList;
    private TextView cancle;
    private TextView confirm;
    private OptionSelectedCallBack callBack;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
//    private String province;
//    private String city;
//    private String district;
    private String str;
    private int p;
    private int c;
    private int d;
    private int maxTextSize = 24; //字体最大值
    private int minTextSize = 14; //字体最小值
    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter districtAdapter;

    public PickerViewDialog(Context context, ArrayList<String> provinceList, List<ArrayList<String>> cityList, List<List<ArrayList<String>>> districtList, OptionSelectedCallBack callBack) {
        super(context);
        this.context = context;
        this.provinceList = provinceList;
        this.cityList = cityList;
        this.districtList = districtList;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_area_pickerview);
        initWindow();
        cancle = (TextView) findViewById(R.id.area_picker_cancle);
        confirm = (TextView) findViewById(R.id.area_picker_confirm);
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        mViewProvince.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setTextviewSize((String)provinceAdapter.getItemText(wheel.getCurrentItem()), provinceAdapter);
            }
        });
        mViewCity.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setTextviewSize((String)cityAdapter.getItemText(wheel.getCurrentItem()), cityAdapter);
            }
        });
        mViewDistrict.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setTextviewSize((String)districtAdapter.getItemText(wheel.getCurrentItem()), districtAdapter);
            }
        });
        cancle.setOnClickListener(this);
        confirm.setOnClickListener(this);
        provinceAdapter = new AddressTextAdapter(context, provinceList,0,24,14);
        cityAdapter = new AddressTextAdapter(context,cityList.get(0),0,24,14);
        districtAdapter = new AddressTextAdapter(context,districtList.get(0).get(0),0,24,14);
        mViewProvince.setViewAdapter(provinceAdapter);
        mViewCity.setViewAdapter(cityAdapter);
        mViewDistrict.setViewAdapter(districtAdapter);
        // 设置可见条目数量
        mViewProvince.setVisibleItems(5);
        mViewCity.setVisibleItems(5);
        mViewDistrict.setVisibleItems(5);
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
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.area_picker_cancle:
                callBack.cancle();
                break;
            case R.id.area_picker_confirm:
                if ("北京市".equals(provinceList.get(p)) || "上海市".equals(provinceList.get(p)) || "天津市".equals(provinceList.get(p)) || "重庆市".equals(provinceList.get(p)) || "澳门".equals(provinceList.get(p)) || "香港".equals(provinceList.get(p))) {
                    str = provinceList.get(p) + "," + districtList.get(p).get(c).get(d);
                } else {
                    str = provinceList.get(p) + "," + cityList.get(p).get(c) + "," + districtList.get(p).get(c).get(d);
                }
                callBack.confirm(str);
                break;
            default:
                break;
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        switch (wheel.getId()) {
            case R.id.id_province:
//                province = provinceList.get(newValue);
                p = newValue;
                cityAdapter = new AddressTextAdapter(context,cityList.get(p),0,24,14);
                districtAdapter = new AddressTextAdapter(context,districtList.get(p).get(0),0,24,14);
                mViewCity.setViewAdapter(cityAdapter);
                mViewCity.setCurrentItem(0);
                mViewDistrict.setViewAdapter(districtAdapter);
                mViewDistrict.setCurrentItem(0);
//                cityAdapter.updateAdapter(cityList.get(p));
//                districtAdapter.updateAdapter(districtList.get(p).get(0));
                break;
            case R.id.id_city:
//                city = cityList.get(p).get(newValue);
                c = newValue;
                districtAdapter = new AddressTextAdapter(context,districtList.get(p).get(c),0,24,14);
                mViewDistrict.setViewAdapter(districtAdapter);
                mViewDistrict.setCurrentItem(0);
//                districtAdapter.updateAdapter(districtList.get(p).get(c));
                break;
            case R.id.id_district:
//                district = districtList.get(p).get(c).get(newValue);
                d = newValue;
                break;
            default:
                break;
        }
    }
    private class AddressTextAdapter extends AbstractWheelTextAdapter {
        private ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.picker_view_list_item, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }

        public void updateAdapter(ArrayList<String> list) {
            this.list.clear();
            this.list = list;
            notifyDataChangedEvent();
        }
    }

    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(24);
            } else {
                textvew.setTextSize(14);
            }
        }
    }
}
