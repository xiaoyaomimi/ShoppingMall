package com.mimile.onlinestore.util;

/**
 * Created by caidongdong on 2016/12/16 09:43
 * email : mircaidong@163.com
 */
public interface CartWaresCheckCallBack {
    /**
     * 是否所有商品都被选中
     * @param position
     */
    void storeWaresCheckedAll(int position,boolean status);

    /**
     * 隐藏或显示商店所有商品的编辑界面
     * @param startpoisiton
     * @param endposition
     * @param status
     */
    void showOrHideChildModifyLayout(int startpoisiton,int endposition,boolean status);

    /**
     * 修改总价和数量
     * @param price
     * @param num
     */
    void changeWaresNumAndTotalPrice(float price,int num);
}
