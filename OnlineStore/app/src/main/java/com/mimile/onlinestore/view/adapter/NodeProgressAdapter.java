package com.mimile.onlinestore.view.adapter;

import com.mimile.onlinestore.entity.LogisticsData;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/21 14:56
 * email : mircaidong@163.com
 */

public interface NodeProgressAdapter {
    /**
     * 返回集合大小
     *
     * @return
     */
    int getCount();

    /**
     * 适配数据的集合
     *
     * @return
     */
    List<LogisticsData> getData();
}
