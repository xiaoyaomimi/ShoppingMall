package com.mimile.dropdownmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mimile.dropdownmenu.R;

import java.util.List;


public class DropDownAdapter extends BaseAdapter{
    protected Context context;
    protected List<String> list;
    protected int checkItemPosition = 0;

    public DropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public void setCheckItem(String name) {
        if(name == null) return;
        for(int i=0;i<list.size();i++){
            if(name.equals(list.get(i))){
                checkItemPosition = i;
                notifyDataSetChanged();
                break;
            }
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(inflateItemView(), null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.mText.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                actionSelect(viewHolder.mText);
            } else {
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                actionNotSelect(viewHolder.mText);
            }
        }
    }

    static class ViewHolder {
        TextView mText;

        ViewHolder(View view) {
            mText = (TextView) view.findViewById(R.id.text);
        }
    }

    /**------------------------------必须重要写这里的方法----------------*/
    protected int inflateItemView(){
        return 0;
    }

    protected void actionSelect(TextView textView){}
    protected void actionNotSelect(TextView textView){}

    /**------------------------------必须要重写这里的方法----------------*/
}
