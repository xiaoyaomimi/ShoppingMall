package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/14 14:53
 * email : mircaidong@163.com
 */
public class Wares {
    private long id;
    private String name;
    private String imgUrl;
    private String detailUrl;
    private String description;
    private String storeId;
    private String storeName;
    private Float shipPrice;
    private Float price;
    private long sellNum;

    public Wares() {
    }

    public Wares(long id, String name, String imgUrl, String detailUrl, String description, String storeId, String storeName, Float shipPrice, Float price, long sellNum) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.detailUrl = detailUrl;
        this.description = description;
        this.storeId = storeId;
        this.storeName = storeName;
        this.shipPrice = shipPrice;
        this.price = price;
        this.sellNum = sellNum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Float getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(Float shipPrice) {
        this.shipPrice = shipPrice;
    }

    public long getSellNum() {
        return sellNum;
    }

    public void setSellNum(long sellNum) {
        this.sellNum = sellNum;
    }

    @Override
    public String toString() {
        return "Wares{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", description='" + description + '\'' +
                ", storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", shipPrice=" + shipPrice +
                ", price=" + price +
                ", sellNum=" + sellNum +
                '}';
    }
}
