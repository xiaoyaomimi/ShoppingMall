package com.mimile.onlinestore.view.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.JsonFileReader;
import com.mimile.onlinestore.util.OptionSelectedCallBack;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/30 10:11
 * email : mircaidong@163.com
 */

public class PhoneNumAreaSelectDialog extends Dialog {
    private Context context;
    private ListView listView;
    private List<String> areaList;
    private OptionSelectedCallBack callBack;

    public PhoneNumAreaSelectDialog(Context context,OptionSelectedCallBack callBack) {
        super(context);
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popwin_area_select);
        listView = (ListView) findViewById(R.id.popwin_area_select_lv);
        getData();
        listView.setAdapter(new AreaAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack.confirm(areaList.get(position));
                dismiss();
            }
        });
    }

    private void getData() {
        areaList = new ArrayList<>();
        String area = JsonFileReader.getJson(context,"area_phone.json");
        try {
            JSONArray jsonArray = new JSONArray(area);
            for (int i = 0; i < jsonArray.length(); i++) {
                areaList.add(jsonArray.getString(i));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    class AreaAdapter extends BaseAdapter{

        public AreaAdapter() {
        }

        @Override
        public int getCount() {
            if (areaList == null)
                return 0;
            return areaList.size();
        }

        @Override
        public Object getItem(int position) {
            return areaList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.popwin_area_select_item,null);
                holder = new ViewHolder();
                holder.areaName = (TextView) convertView.findViewById(R.id.popwin_area_select_item_tv);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.areaName.setText(areaList.get(position));
            return convertView;
        }

        class ViewHolder{
            TextView areaName;
        }
    }


}
