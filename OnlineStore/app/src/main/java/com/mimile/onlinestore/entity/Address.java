package com.mimile.onlinestore.entity;

/**
 * Created by caidongdong on 2016/12/6 13:37
 * email : mircaidong@163.com
 */
public class Address {
    private String userName;        //收件人名称
    private String sortLetter;      //排序字母
    private String phoneNum;        //收件人电话
    private String zipCode;         //邮编
    private String zoneName;        //地区
    private String streetName;      //街道
    private String detailAddress;   //详细地址
    private int active;             //0表示未激活，1表示激活
    private String userID;           //用户ID

    public Address() {
    }

    public Address(String userName, String phoneNum, String zipCode, String zoneName, String streetName, String detailAddress, int active, String userID, String sortLetter) {
        this.userName = userName;
        this.sortLetter = sortLetter;
        this.phoneNum = phoneNum;
        this.zipCode = zipCode;
        this.zoneName = zoneName;
        this.streetName = streetName;
        this.detailAddress = detailAddress;
        this.active = active;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSortLetter() {
        return sortLetter;
    }

    public void setSortLetter(String sortLetter) {
        this.sortLetter = sortLetter;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userName='" + userName + '\'' +
                ", sortLetter='" + sortLetter + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", active=" + active +
                ", userID='" + userID + '\'' +
                '}';
    }
}
