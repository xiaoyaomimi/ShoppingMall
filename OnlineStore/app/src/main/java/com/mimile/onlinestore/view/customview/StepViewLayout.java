package com.mimile.onlinestore.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.LogisticsData;
import com.mimile.onlinestore.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/12/21 17:49
 * email : mircaidong@163.com
 */

public class StepViewLayout extends LinearLayout {
    private int highDotColor;
    private int defaultDotColor;
    private int radius;
    private int dotPosition;

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<LogisticsData> datas;

    public StepViewLayout(Context context) {
        this(context, null);
    }

    public StepViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepViewLayout, defStyleAttr, 0);
        highDotColor = a.getColor(R.styleable.StepViewLayout_dotHighColor, Color.parseColor("#1c980f"));
        defaultDotColor = a.getColor(R.styleable.StepViewLayout_dotDefaultDotColor, Color.parseColor("#d0d0d0"));
        radius = (int) a.getDimension(R.styleable.StepViewLayout_dotRadius, DisplayUtil.dp2px(context, -1));
        dotPosition = a.getInteger(R.styleable.StepViewLayout_dotPosition, StepView.POSITION_CENTER);
        a.recycle();

        mContext = context;
        init();
    }

    private void init() {
        layoutInflater = LayoutInflater.from(mContext);
        setOrientation(VERTICAL);
        datas = new ArrayList<>();
    }

    /**
     * 设置数据,使用默认的布局样式
     * @param data
     */
    public void setData(List<LogisticsData> data) {
        if (data == null)
            return;
        this.datas = data;
        inflateContent();
    }

    private void setStepDotViewStyle(StepView dotView,int position){
        dotView.setDotPosition(dotPosition);
        dotView.setHighDotColor(highDotColor);
        dotView.setDefaultDotColor(defaultDotColor);
        dotView.setRadius(radius);
        if (position == 0) {
            dotView.setFirstDot();
        } else if (position == datas.size() - 1) {
            dotView.setLastDot();
        }
    }

    private void inflateContent() {
        for (int i = 0; i < datas.size(); i++) {
            View contentView = layoutInflater.inflate(R.layout.step_view, null);
            StepView dotView = (StepView) contentView.findViewById(R.id.stepView);
            setStepDotViewStyle(dotView,i);

            TextView msg = (TextView) contentView.findViewById(R.id.itemMsg);
            TextView date = (TextView) contentView.findViewById(R.id.itemDate);
            LogisticsData data = datas.get(i);
            if (i == 0) {
                msg.setTextColor(highDotColor);
                date.setTextColor(highDotColor);
            }
            msg.setText(data.getMsg());
            date.setText(data.getDate());

            addView(contentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }


    /**
     * 设置数据,并使用自定义的布局,该布局的填充需要自己在回调方法中设置样式
     * @param data
     * @param layoutId
     * @param inflateContentListener
     */
    public void setDataAndView(List data, int layoutId, InflateContentListener inflateContentListener) {
        this.datas = data;
        for (int i = 0; i < datas.size(); i++) {

            LinearLayout stepItem = new LinearLayout(mContext);
            stepItem.setOrientation(LinearLayout.HORIZONTAL);

            StepView stepDotView = new StepView(mContext);
            setStepDotViewStyle(stepDotView, i);
            LinearLayout.LayoutParams dotParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dotParam.rightMargin = DisplayUtil.dp2px(mContext, 10);
            stepItem.addView(stepDotView, dotParam);

            View contentView = layoutInflater.inflate(layoutId, null);
            LinearLayout.LayoutParams contentParam = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParam.weight = 1;
            stepItem.addView(contentView, contentParam);

            addView(stepItem, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            if (inflateContentListener != null) {
                inflateContentListener.onContentInflate(i, contentView);
            }
        }
    }

    public interface InflateContentListener {
        void onContentInflate(int position, View contentView);
    }

}
