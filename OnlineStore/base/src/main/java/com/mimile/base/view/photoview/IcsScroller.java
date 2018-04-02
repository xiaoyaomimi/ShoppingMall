package com.mimile.base.view.photoview;

import android.content.Context;

/**
 * Created by caidongdong on 2016/12/28 15:56
 * email : mircaidong@163.com
 */

public class IcsScroller extends GingerScroller {
    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }
}
