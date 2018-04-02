package com.mimile.base.view.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by caidongdong on 2016/12/28 09:51
 * email : mircaidong@163.com
 * 通用贝塞尔曲线动画类
 */

public class CustomAnim {
    private Context context;
    private View startView;
    private View targetView;
    private int[] parentLocation;
    private int[] startLoc;
    private int[] endLoc;
    private int resourceId;
    private PathMeasure pathMeasure;
    private float[] currentPosition;

    public CustomAnim(Context context, View startView, View targetView, int resourceId) {
        this.context = context;
        this.startView = startView;
        this.targetView = targetView;
        this.resourceId = resourceId;
    }

    public void startAnim() {
        final ImageView img = new ImageView(context);
        img.setImageResource(resourceId);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
        final RelativeLayout rl = new RelativeLayout(context);
        rl.addView(img,params);
        parentLocation = new int[2];
        rl.getLocationInWindow(parentLocation);
        startLoc = new int[2];
        startView.getLocationInWindow(startLoc);
        endLoc = new int[2];
        targetView.getLocationInWindow(endLoc);
        float startX = startLoc[0] - parentLocation[0] + startView.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + startView.getHeight() / 2;
        float toX = endLoc[0] - parentLocation[0] + targetView.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];
        Path path = new Path();
        path.moveTo(0,0);
        path.quadTo(0, 0, 1000, 1000);
        pathMeasure = new PathMeasure(path,false);
        currentPosition = new float[2];
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,pathMeasure.getLength());
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                pathMeasure.getPosTan(value,currentPosition,null);
                img.setTranslationX(currentPosition[0]);
                img.setTranslationY(currentPosition[1]);
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rl.removeView(img);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
