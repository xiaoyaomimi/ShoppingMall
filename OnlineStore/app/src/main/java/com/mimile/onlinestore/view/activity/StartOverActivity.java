package com.mimile.onlinestore.view.activity;


import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.mimile.base.util.BaseSharedPreference;
import com.mimile.base.view.activity.BaseActivity;
import com.mimile.onlinestore.R;
/**
 * Created by caidongdong on 2016/12/6 14:50
 * email : mircaidong@163.com
 */
public class StartOverActivity extends BaseActivity {
    private BaseSharedPreference sharedPreference;
    @Override
    public void initView() {
        final View startView = View.inflate(this, R.layout.activity_start_over, null);
        setContentView(startView);
        //渐变
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        startView.setAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        // TODO Auto-generated method stub
                                        redirectto();
                                    }
                                }
        );
    }

    private void redirectto() {
        sharedPreference = new BaseSharedPreference(this);
        boolean isFirst = sharedPreference.getBoolean("is_first_run");
        Intent intent = null;
        if (!isFirst) {
            intent = new Intent(this, GuidePageActivity.class);
        }else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
