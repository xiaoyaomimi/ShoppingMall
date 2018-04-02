package com.mimile.base.view.photoview;

/**
 * Created by caidongdong on 2016/12/28 15:37
 * email : mircaidong@163.com
 */

public interface OnGestureListener {
    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);
}
