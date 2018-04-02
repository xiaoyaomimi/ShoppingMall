package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/11/30 09:31
 * email : mircaidong@163.com
 */
public class HeaderMenu {
    private String imgUrl;      //菜单图片
    private String describe;    //菜单文字描述
    private boolean enable;    //是否可用
    private String htmlUrl;     //是否有网页链接
    private float version;     //版本号

    public HeaderMenu() {
    }

    public HeaderMenu(String imgUrl, String describe, boolean enable, String htmlUrl, float version) {
        this.imgUrl = imgUrl;
        this.describe = describe;
        this.enable = enable;
        this.htmlUrl = htmlUrl;
        this.version = version;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HeaderMenu{" +
                "imgUrl='" + imgUrl + '\'' +
                ", describe='" + describe + '\'' +
                ", enable=" + enable +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", version=" + version +
                '}';
    }
}
