package com.mimile.onlinestore.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by caidongdong on 2016/12/1 11:04
 * email : mircaidong@163.com
 */
public class HomeCampaign implements Parcelable, MultiItemEntity {
    private Long id;
    private String title;
    private Campaign cpOne;
    private Campaign cpTwo;
    private Campaign cpThree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Campaign getCpOne() {
        return cpOne;
    }

    public void setCpOne(Campaign cpOne) {
        this.cpOne = cpOne;
    }

    public Campaign getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(Campaign cpTwo) {
        this.cpTwo = cpTwo;
    }

    public Campaign getCpThree() {
        return cpThree;
    }

    public void setCpThree(Campaign cpThree) {
        this.cpThree = cpThree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeParcelable(this.cpOne, flags);
        dest.writeParcelable(this.cpTwo, flags);
        dest.writeParcelable(this.cpThree, flags);
    }

    public HomeCampaign() {
    }

    public HomeCampaign(Long id, String title, Campaign cpOne, Campaign cpTwo, Campaign cpThree) {
        this.id = id;
        this.title = title;
        this.cpOne = cpOne;
        this.cpTwo = cpTwo;
        this.cpThree = cpThree;
    }

    protected HomeCampaign(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.cpOne = in.readParcelable(Campaign.class.getClassLoader());
        this.cpTwo = in.readParcelable(Campaign.class.getClassLoader());
        this.cpThree = in.readParcelable(Campaign.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeCampaign> CREATOR = new Parcelable.Creator<HomeCampaign>() {
        @Override
        public HomeCampaign createFromParcel(Parcel source) {
            return new HomeCampaign(source);
        }

        @Override
        public HomeCampaign[] newArray(int size) {
            return new HomeCampaign[size];
        }
    };

    @Override
    public String toString() {
        return "HomeCampaign{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cpOne=" + cpOne +
                ", cpTwo=" + cpTwo +
                ", cpThree=" + cpThree +
                '}';
    }

    @Override
    public int getItemType() {
        if (cpTwo == null && cpThree == null){
            return 0;
        }else if (cpTwo != null && cpThree == null) {
            return 1;
        }else {

        }
        return (int)(id % 2) + 2;
    }
}
