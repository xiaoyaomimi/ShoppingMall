package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/1 17:45
 * email : mircaidong@163.com
 */
public class User {
    private String id;
    private String userName;
    private String key;
    private int is_seller;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIs_seller() {
        return is_seller;
    }

    public void setIs_seller(int is_seller) {
        this.is_seller = is_seller;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", key='" + key + '\'' +
                ", is_seller=" + is_seller +
                '}';
    }
}
