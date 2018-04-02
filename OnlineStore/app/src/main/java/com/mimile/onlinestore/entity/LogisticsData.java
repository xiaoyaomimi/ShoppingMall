package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/21 14:57
 * email : mircaidong@163.com
 */

public class LogisticsData {
    private String msg;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "LogisticsData{" +
                "msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
