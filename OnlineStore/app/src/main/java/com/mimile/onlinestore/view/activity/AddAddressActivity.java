package com.mimile.onlinestore.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.JsonFileReader;
import com.mimile.onlinestore.util.OptionSelectedCallBack;
import com.mimile.onlinestore.view.customview.PickerViewDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class AddAddressActivity extends CommonBaseActivity implements View.OnClickListener,OptionSelectedCallBack{
    private ArrayList<String> provinceList;
    private ArrayList<String> cities;
    private ArrayList<ArrayList<String>> cityList;
    private ArrayList<String> district;
    private ArrayList<ArrayList<String>> districts;
    private ArrayList<List<ArrayList<String>>> districtList;
    private PickerViewDialog pickerView;
    private RelativeLayout city;
    private TextView outLineAddress;
    private TextView back;
    private TextView confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_address);
        city = (RelativeLayout) findViewById(R.id.address_pick_area);
        outLineAddress = (TextView) findViewById(R.id.add_address_tv_address_outline);
        back = (TextView) findViewById(R.id.back_tv);
        confirm = (TextView) findViewById(R.id.confirm_tv);
        city.setOnClickListener(this);
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    private void initData() {

        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
        districtList = new ArrayList<>();
        String data = JsonFileReader.getJson(this,"province_data.json");
        parseJson(data);
        pickerView = new PickerViewDialog(this,provinceList,cityList,districtList,this);
    }

    /**
     * 解析json填充集合
     * @param str
     */
    public void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceList.add(provinceName);
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_pick_area:
                initData();
                pickerView.show();
                break;
            case R.id.back_tv:
                finish();
                break;
            case R.id.confirm_tv:
                //TODO
                finish();
            default:
                break;
        }
    }

    @Override
    public void cancle() {
        pickerView.dismiss();
    }

    @Override
    public void confirm(String str) {
        outLineAddress.setText(str);
        pickerView.dismiss();
    }
}
