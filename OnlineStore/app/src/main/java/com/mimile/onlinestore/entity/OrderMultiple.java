package com.mimile.onlinestore.entity;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/15 15:44
 * email : mircaidong@163.com
 */
public class OrderMultiple {
    private CartStore  store;
    private List<CartWares> waresList;

    public CartStore getStore() {
        return store;
    }

    public void setStore(CartStore store) {
        this.store = store;
    }

    public List<CartWares> getWaresList() {
        return waresList;
    }

    public void setWaresList(List<CartWares> waresList) {
        this.waresList = waresList;
    }

    @Override
    public String toString() {
        return "OrderMultiple{" +
                "store=" + store +
                ", waresList=" + waresList +
                '}';
    }
}
