package com.mimile.onlinestore.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.HeaderMenu;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by caidongdong on 2016/11/30 09:22
 * email : mircaidong@163.com
 */
public class GridViewMenuAdapter extends BaseAdapter implements View.OnClickListener {
    private List<HeaderMenu> headerMenus;
    private Context context;
    private OnGridViewItemClickListener onGridViewItemClickListener = null;

    public GridViewMenuAdapter(Context mcontext,List<HeaderMenu> list) {
        this.context = mcontext;
        this.headerMenus = list;
    }

    /**
     * 当数据方式改变时调用该方法
     * @param list
     */
    public void updateGridView(List<HeaderMenu> list) {
        this.headerMenus = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return headerMenus.size();
    }

    @Override
    public Object getItem(int position) {
        return headerMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        HeaderMenu headerMenu = headerMenus.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_adapter_item,null);
            viewHolder.describe = (TextView) convertView.findViewById(R.id.describe);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageIco);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        imageLoader.DisplayImage(this.headerMenus.get(position).getImgUrl(),viewHolder.imageView);
        Picasso.with(context)
                .load(this.headerMenus.get(position).getImgUrl())
                .placeholder(R.mipmap.food)
                .error(R.mipmap.movie)
                .into(viewHolder.imageView);
        viewHolder.describe.setText(this.headerMenus.get(position).getDescribe());
//        viewHolder.imageView.setImageResource(Integer.parseInt(this.headerMenus.get(position).getImgUrl()));
        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (onGridViewItemClickListener != null) {
            onGridViewItemClickListener.onItemClick(v, (String)v.getTag());
        }
    }

    public void setOnItemClickListener(OnGridViewItemClickListener listener) {
        this.onGridViewItemClickListener = listener;
    }

    final static class ViewHolder {
        TextView describe;
        ImageView imageView;
    }

    public static interface OnGridViewItemClickListener {
        void onItemClick(View view , String data);
    }
}
