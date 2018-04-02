package com.mimile.onlinestore.view.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.OptionSelectedCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by caidongdong on 2017/1/6 13:13
 * email : mircaidong@163.com
 */

public class WaresSortSelectPopwin extends PopupWindow {
    private Context context;
    private String sortKey;
    private OptionSelectedCallBack callBack;
    private View view;
    private ListView listView;
    private List<String> list;

    public WaresSortSelectPopwin(Context context, String sortKey, OptionSelectedCallBack callBack) {
        super(context);
        this.context = context;
        this.sortKey = sortKey;
        this.callBack = callBack;
        initPopWin();
        initData();
    }

    private void initPopWin() {
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        view = LayoutInflater.from(context).inflate(R.layout.popwin_sort_key, null);
        listView = (ListView) view.findViewById(R.id.popwin_sort_key_lv);
        setContentView(view);
        setAnimationStyle(R.anim.sort_menu_in);
    }

    private void initData() {
        String[] strings = context.getResources().getStringArray(R.array.sort_key);
        if (strings != null && strings.length > 0) {
            list = new ArrayList<>();
            Collections.addAll(list, strings);
        }
        listView.setAdapter(new SortSelectAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack.confirm(list.get(position));
                dismiss();
            }
        });
    }

    class SortSelectAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if (list != null && !list.isEmpty())
                return list.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (list != null && !list.isEmpty())
                return list.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_wares_sort_select_popwin,null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.list_wares_sort_select_popwin_tv);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.list_wares_sort_select_popwin_img);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(list.get(position));
            if (sortKey.equals(list.get(position))){
                viewHolder.textView.setTextColor(ContextCompat.getColor(context,R.color.orange));
                viewHolder.imageView.setVisibility(View.VISIBLE);
            }else {
                viewHolder.textView.setTextColor(Color.parseColor("#444444"));
                viewHolder.imageView.setVisibility(View.INVISIBLE);
            }
            return convertView;
        }

        class ViewHolder{
            TextView textView;
            ImageView imageView;
        }
    }

}
