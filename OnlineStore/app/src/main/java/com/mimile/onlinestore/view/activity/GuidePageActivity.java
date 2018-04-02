package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mimile.base.util.BaseSharedPreference;
import com.mimile.base.view.activity.BaseActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.adapter.GuidePageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class GuidePageActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private GuidePageAdapter pagerAdapter;
    private List<View> viewList;
    private Button confirm;
    //引导图片资源
    private static final int[] pics = { R.mipmap.guide01,
            R.mipmap.guide02, R.mipmap.guide03,
            R.mipmap.guide04 };
    //底部小店图片
    private ImageView[] dots ;
    //记录当前选中位置
    private int currentIndex;
    private BaseSharedPreference sharedPreference;

    @Override
    public void initView() {
        setContentView(R.layout.activity_guide_page);
        viewPager = (ViewPager) findViewById(R.id.guide_page_pager);
        confirm = (Button) findViewById(R.id.guide_page_btn_confirm);
        viewList = new ArrayList<View>();
        ViewGroup.LayoutParams mParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //初始化引导图片列表  
        for(int i = 0; i < pics.length; i ++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            viewList.add(iv);
        }
        //初始化Adapter  
        pagerAdapter = new GuidePageAdapter(viewList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
        //初始化底部小点
        initDots();
        sharedPreference = new BaseSharedPreference(this);
        sharedPreference.setBoolean("is_first_run",true);
    }

    /**
     * 初始化小点
     */
    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];
        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);//都设为灰色
            dots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);//设置为白色，即选中状态
    }

    /**
     *设置当前的引导页
     */
    private void setCurView(int position)
    {
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     *这只当前引导小点的选中
     */
    private void setCurDot(int positon)
    {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }
        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = positon;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == pics.length - 1) {
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GuidePageActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            confirm.setVisibility(View.VISIBLE);
        }
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
