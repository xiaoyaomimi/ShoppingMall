package com.mimile.onlinestore.util;

import android.content.Context;
import android.widget.ImageView;

import com.mimile.onlinestore.R;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by caidongdong on 2016/11/29 11:25
 * email : mircaidong@163.com
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.with(context)
                .load((String)path)
                .placeholder(R.mipmap.placeimg)
                .error(R.mipmap.background)
                .into(imageView);
    }
}
