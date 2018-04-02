package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/15 11:29
 * email : mircaidong@163.com
 */
public class CartStore extends Store {
    private int count;
    private boolean isChecked = false;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
