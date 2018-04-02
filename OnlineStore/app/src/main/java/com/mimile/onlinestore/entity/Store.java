package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/15 11:23
 * email : mircaidong@163.com
 */
public class Store {
    private long id;
    private String name;
    private String level;
    private String icoUrl;
    private String storeUrl;

    public Store(long id, String name, String level, String icoUrl, String storeUrl) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.icoUrl = icoUrl;
        this.storeUrl = storeUrl;
    }

    public Store() {
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", icoUrl='" + icoUrl + '\'' +
                ", storeUrl='" + storeUrl + '\'' +
                '}';
    }
}
