package com.mimile.onlinestore.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Address;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/6 13:39
 * email : mircaidong@163.com
 */
public class AddressAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private List<Address> list;

    public AddressAdapter(Context mContext, List<Address> list) {
        this.mContext = mContext;
        this.list = list;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<Address> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @SuppressLint("ResourceAsColor")
    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final Address mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.address_list_item, null);
            viewHolder.name = (TextView) view.findViewById(R.id.address_user_name);
            viewHolder.phoneNum = (TextView) view.findViewById(R.id.address_user_phone);
            viewHolder.addressDesc = (TextView) view.findViewById(R.id.address_user_address);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.cb = (RadioButton) view.findViewById(R.id.address_list_item_radiobtn);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetter());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        Address as = this.list.get(position);

        int active = as.getActive();
        if (active == 1) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        viewHolder.name.setText(as.getUserName());
        viewHolder.phoneNum.setText(as.getPhoneNum());

        String detailAddress = as.getZoneName() + as.getStreetName() + as.getDetailAddress();
        viewHolder.addressDesc.setText(detailAddress);
        return view;
    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView name;
        TextView phoneNum;
        TextView addressDesc;
        RadioButton cb;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {

        return list.get(position).getSortLetter().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetter();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

}