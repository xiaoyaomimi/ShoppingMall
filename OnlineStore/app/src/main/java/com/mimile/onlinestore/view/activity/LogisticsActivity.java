package com.mimile.onlinestore.view.activity;

import android.os.Bundle;

import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.LogisticsData;
import com.mimile.onlinestore.view.customview.StepViewLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/21 11:50
 * email : mircaidong@163.com
 */
public class LogisticsActivity extends CommonBaseActivity {
    private StepViewLayout stepView;
    private List<LogisticsData> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        stepView = (StepViewLayout) findViewById(R.id.logistics_activity_stepview);
        initStepView();
    }

    private void initStepView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LogisticsData data = new LogisticsData();
            if (i % 2 == 0) {
                data.setMsg("[北京市] 包裹已到达,北京市朝阳区 \n 联系电话:15912345678 ");
            } else {
                data.setMsg("[杭州市] 包裹已派发至转运中心,转运中心已发出。");
            }
            data.setDate("2016年08月03日");
            datas.add(data);
        }
        stepView.setData(datas);
    }
}
