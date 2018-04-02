package com.mimile.onlinestore.util;

/**
 * 通用回调接口
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.caidongdong.aesmovie.util.CommonCallback.java
 * @author: caidongdong
 * @date: 2016-08-22 17:03
 */
public interface CommonCallback<T> {
    void onSuccess(T t);
    void onFailure(String error);
}
