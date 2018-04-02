package com.mimile.base.view.photoview;

import android.view.MotionEvent;

/**
 * Created by caidongdong on 2016/12/28 15:42
 * email : mircaidong@163.com
 */

public interface GestureDetector {
    boolean onTouchEvent(MotionEvent ev);

    boolean isScaling();

    boolean isDragging();

    void setOnGestureListener(OnGestureListener listener);
}
