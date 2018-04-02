package com.mimile.onlinestore.util.enums;

/**
 * Created by caidongdong on 2016/12/1 11:14
 * email : mircaidong@163.com
 * 首页广告显示方式枚举类型
 */
public enum ECampaign {
    ONE_CENTER(0),TWO_CENTER(1),THREE_LEFT(2),THREE_RIGHT(3);

    private int value;

    ECampaign(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static ECampaign valueOf(int value) {
        for (ECampaign type : values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("not support SelectType");
    }
}
