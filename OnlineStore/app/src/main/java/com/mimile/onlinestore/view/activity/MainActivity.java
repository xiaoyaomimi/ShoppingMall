package com.mimile.onlinestore.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mimile.base.view.MyViewPager;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.view.fragment.FragmentFactory;

import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;
import static android.widget.RadioGroup.OnCheckedChangeListener;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class MainActivity extends FragmentActivity {
    private RadioGroup mainGroup;
    private RadioButton rbMe;
    private RadioButton rbCart;
    private RadioButton rbFind;
    private RadioButton rbKind;
    private RadioButton rbHome;
    private MyViewPager fragmentContent;
    //导航栏radio个数
    static final int NUM_ITEMS = 5;
    public Myadapter madapter;
    private Context context;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        madapter = new Myadapter(getSupportFragmentManager());
        initView();
        initData();
        fragmentContent.setAdapter(madapter);
    }

    private void initView() {
        mainGroup = (RadioGroup) findViewById(R.id.main_group);
        rbMe = (RadioButton) findViewById(R.id.rb_me);
        rbCart = (RadioButton) findViewById(R.id.rb_cart);
        rbFind = (RadioButton) findViewById(R.id.rb_find);
        rbKind = (RadioButton) findViewById(R.id.rb_kind);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        fragmentContent = (MyViewPager) findViewById(R.id.fragment_content);
    }
    private void initData() {
        mainGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        fragmentContent.setCurrentItem(0);
                        break;
                    case R.id.rb_kind:
                        fragmentContent.setCurrentItem(1);
                        break;
                    case R.id.rb_find:
                        fragmentContent.setCurrentItem(2);
                        break;
                    case R.id.rb_cart:
                        fragmentContent.setCurrentItem(3);
                        break;
                    case R.id.rb_me:
                        fragmentContent.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
        //默认选择第一页
        mainGroup.check(R.id.rb_home);
        //viewpager滑动监听
        fragmentContent.setOnPageChangeListener(new MyViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mainGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        mainGroup.check(R.id.rb_kind);
                        break;
                    case 2:
                        mainGroup.check(R.id.rb_find);
                        break;
                    case 3:
                        mainGroup.check(R.id.rb_cart);
                        break;
                    case 4:
                        mainGroup.check(R.id.rb_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //从FragmentFactory中取得对应的界面
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), getString(R.string.press_again_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
