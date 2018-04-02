package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/14 14:54
 * email : mircaidong@163.com
 */
public class CartWares extends Wares {
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

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void changeCheckedState() {
        if (isChecked) {
            isChecked = false;
        }else {
            isChecked = true;
        }
    }

}
