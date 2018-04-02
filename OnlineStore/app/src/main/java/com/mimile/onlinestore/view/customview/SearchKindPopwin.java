package com.mimile.onlinestore.view.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.OptionSelectedCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by caidongdong on 2017/1/5 14:57
 * email : mircaidong@163.com
 */

public class SearchKindPopwin extends PopupWindow {
    private Context context;
    private View view;
    private ListView listView;
    private List<String> list;
    private OptionSelectedCallBack callBack;

    public SearchKindPopwin(Context context,OptionSelectedCallBack callBack) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.callBack = callBack;
    }

    public SearchKindPopwin(Context context, int with, int height) {
        this.context = context;
        setWidth(with);
        setHeight(height);
        //设置可以获得焦点
        setFocusable(true);
        //设置弹窗内可点击
        setTouchable(true);
        //设置弹窗外可点击
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        view = LayoutInflater.from(context).inflate(R.layout.popwin_search_kind, null);
        setContentView(view);
//        setAnimationStyle(R.style.popwin_anim_style);
        initData();
    }

    private void initData() {
        listView = (ListView) view.findViewById(R.id.popwin_search_kind_lv);
        String[] strings = context.getResources().getStringArray(R.array.search_kind);
        if (strings != null && strings.length > 0) {
            list = new ArrayList<String>();
            Collections.addAll(list, strings);
        }
        //设置列表的适配器
        listView.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = null;

                if (convertView == null) {
                    textView = new TextView(context);
                    textView.setTextColor(Color.rgb(255, 255, 255));
                    textView.setTextSize(14);
                    textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    //设置文本居中
                    textView.setGravity(Gravity.CENTER_VERTICAL);
                    //设置文本域的范围
                    textView.setPadding(20,10,10,10);
                    //设置文本在一行内显示（不换行）
                    textView.setSingleLine(true);
                } else {
                    textView = (TextView) convertView;
                }
                //设置文本文字
                textView.setText(list.get(position));

                //设置在文字的左边放一个图标
                Drawable drawable = null;
                if (position == 0) {
                    drawable = ContextCompat.getDrawable(context,R.drawable.ic_wares);
                } else if (position == 1) {
                    drawable = ContextCompat.getDrawable(context,R.drawable.ic_store_white);
                } else {
                    drawable = ContextCompat.getDrawable(context,R.drawable.ic_store_white);
                }
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()) ;
                textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                //设置文字与图标的间隔
                textView.setCompoundDrawablePadding(30);
                return textView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callBack.confirm(list.get(position));
                dismiss();
            }
        });
    }
}
