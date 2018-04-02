package com.mimile.onlinestore.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mimile.base.view.activity.BaseActivity;
import com.mimile.base.view.photoview.CustomViewPager;
import com.mimile.base.view.photoview.PhotoView;
import com.mimile.base.view.photoview.PhotoViewAttacher;
import com.mimile.onlinestore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoViewPagerActivity extends BaseActivity {
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_pager);
        ViewPager mViewPager = (CustomViewPager) findViewById(R.id.photo_view_pager);
        linearLayout = (LinearLayout) findViewById(R.id.photo_view_dots_ll);
        ArrayList<String> imgs = getIntent().getStringArrayListExtra("WARES_IMAGES");
        int currentNum = getIntent().getIntExtra("CURRENT", 0);
        if (imgs != null && imgs.size() > 0) {
            for (int i = 0; i < imgs.size(); i++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.guide_page_dot);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
                lp.setMargins(15, 0, 0, 0);// 设置间距
                imageView.setLayoutParams(lp);
                if (currentNum == i)
                    imageView.setEnabled(false);
                imageView.setId(i);
                linearLayout.addView(imageView);
            }
            mViewPager.setAdapter(new SamplePagerAdapter(imgs, this));
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        if (position == i) {
                            linearLayout.getChildAt(i).setEnabled(false);
                        }else {
                            linearLayout.getChildAt(i).setEnabled(true);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            finish();
        }

    }

    @Override
    public void initView() {

    }


    static class SamplePagerAdapter extends PagerAdapter {

        private List<String> sDrawables;
        private Context context;

        public SamplePagerAdapter(List<String> sDrawables, Context context) {
            this.sDrawables = sDrawables;
            this.context = context;
        }

        @Override
        public int getCount() {
            return sDrawables.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Picasso.with(context)
                    .load(sDrawables.get(position))
                    .placeholder(R.mipmap.placeimg)
                    .error(R.mipmap.background)
                    .into(photoView);
            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    ((Activity) context).finish();
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
