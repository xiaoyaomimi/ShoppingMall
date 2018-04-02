package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.mimile.base.view.activity.BaseActivity;
import com.mimile.base.view.anim.NXHooldeView;
import com.mimile.base.view.graphicdetial.GradationScrollView;
import com.mimile.base.view.graphicdetial.GraphicDetailLayout;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.util.DisplayUtil;
import com.mimile.onlinestore.util.GlideImageLoader;
import com.mimile.onlinestore.view.customview.WaresOptDialog;
import com.mimile.onlinestore.view.fragment.EvaluateFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class WaresDetialActivity extends BaseActivity implements GradationScrollView.ScrollViewListener,View.OnClickListener{
    private Banner waresDetialBanner;
    private ArrayList<String> imgList;
    private WebView webView;
    private RelativeLayout titleBar;
    private GradationScrollView scrollView;
    private RelativeLayout titleBarCircle;
    private RelativeLayout titleBarNormal;
    private RelativeLayout optKindRl;
    private TextView buyNow;
    private TextView addToCart;
    private ImageView waresCart;
    private ImageView waresCartWhite;
    private TextView viewEvaluate;
    private GraphicDetailLayout graphicDetailLayout;
    private TextView back;
    private EvaluateFragment evaluateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wares_detial);
        waresDetialBanner = (Banner) findViewById(R.id.wares_detial_banner);
        webView = (WebView) findViewById(R.id.wares_detail_wb);
        titleBar = (RelativeLayout) findViewById(R.id.wares_detail_title_bar);
        scrollView = (GradationScrollView) findViewById(R.id.wares_detail_top_sv);
        titleBarCircle = (RelativeLayout) findViewById(R.id.wares_detail_title_bar_circle);
        titleBarNormal = (RelativeLayout) findViewById(R.id.wares_detail_title_bar_normal);
        optKindRl = (RelativeLayout) findViewById(R.id.wares_detail_opt_kind_rl);
        buyNow = (TextView) findViewById(R.id.wares_detial_buy_now);
        addToCart = (TextView) findViewById(R.id.wares_detial_join_cart);
        back = (TextView) findViewById(R.id.activity_wares_detial_back_tv);
        addToCart.setOnClickListener(this);
        buyNow.setOnClickListener(this);
        optKindRl.setOnClickListener(this);
        waresCart = (ImageView) findViewById(R.id.wares_detial_cart);
        waresCartWhite = (ImageView) findViewById(R.id.wares_detial_cart_white);
        viewEvaluate = (TextView) findViewById(R.id.wares_detail_top_view_all_evaluate_tv);
        graphicDetailLayout = (GraphicDetailLayout) findViewById(R.id.wares_detail_graphic_layout);
        viewEvaluate.setOnClickListener(this);
        back.setOnClickListener(this);
        initBanner();
        initData();
        setBadgeCount(waresCart,5);
        setBadgeCount(waresCartWhite,5);

    }

    @Override
    public void initView() {

    }

    private void initData() {
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setAppCacheEnabled(true);
        webView.loadUrl("http://huaban.com/pins/966576023/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        scrollView.setScrollViewListener(this);
    }

    private void initBanner() {
        imgList = new ArrayList<>();
        imgList.add("http://www.pp3.cn/uploads/201504/2015040202.jpg");
        imgList.add("http://photo.enterdesk.com/2010-11-2/enterdesk.com-5CA99D47E472CBF6C9FC93C4B07709B8.jpg");
        imgList.add("http://img.tuku.cn/file_big/201504/4a2850e750db42d086adb8b9719643e1.jpg");
        waresDetialBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        waresDetialBanner.setImageLoader(new GlideImageLoader());
        waresDetialBanner.setImages(imgList);
        waresDetialBanner.setBannerAnimation(Transformer.DepthPage);
        waresDetialBanner.isAutoPlay(false);
        waresDetialBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(WaresDetialActivity.this,PhotoViewPagerActivity.class);
                intent.putStringArrayListExtra("WARES_IMAGES",imgList);
                startActivity(intent);
            }
        });
        waresDetialBanner.setIndicatorGravity(BannerConfig.CENTER);
        waresDetialBanner.start();
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        int height = DisplayUtil.dp2px(this,50);
        if (y <= 0) {   //设置标题的背景颜色
            titleBar.setBackgroundColor(Color.argb((int) 0, 255,255,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
//            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1,24,28));
            titleBar.setBackgroundColor(Color.argb((int) alpha, 255,64,129));
            titleBarCircle.setVisibility(VISIBLE);
            titleBarNormal.setVisibility(View.INVISIBLE);
        } else {    //滑动到banner下面设置普通颜色
            titleBar.setBackgroundColor(Color.argb((int) 255, 255,64,129));
            titleBarCircle.setVisibility(View.INVISIBLE);
            titleBarNormal.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wares_detail_opt_kind_rl:
            case R.id.wares_detial_buy_now :
                WaresOptDialog dialog = new WaresOptDialog(this);
                dialog.show();
                break;
            case R.id.wares_detial_join_cart:
                displayCartAnim();
                break;
            case R.id.wares_detail_top_view_all_evaluate_tv:
                graphicDetailLayout.setVisibility(View.INVISIBLE);
                if (titleBarCircle.getVisibility() == View.VISIBLE) {
                    titleBarCircle.setVisibility(View.INVISIBLE);
                    titleBarNormal.setVisibility(View.VISIBLE);
                }
                back.setText("评价");
                evaluateFragment = new EvaluateFragment();
                getFragmentManager().beginTransaction().add(R.id.wares_detail_fl,evaluateFragment).commit();
                break;
            case R.id.activity_wares_detial_back_tv:
                showDetail();
                break;
            default:
                break;
        }
    }

    private void setBadgeCount(View view,int count){
        BadgeView badgeView = new BadgeView(this);
        badgeView.setTargetView(view);
        badgeView.setBadgeMargin(40,10,15,0);
        badgeView.setBadgeCount(count);
    }

    public void displayCartAnim() {
//        CustomAnim customAnim = new CustomAnim(this,addToCart,titleBarCircle,R.mipmap.ico_qq);
//        customAnim.startAnim();
        NXHooldeView nxHooldeView = new NXHooldeView(this);
        int position[] = new int[2];
        addToCart.getLocationInWindow(position);
        nxHooldeView.setStartPosition(new Point(position[0], position[1]));
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        rootView.addView(nxHooldeView);
        int endPosition[] = new int[2];
        if (titleBarCircle.getVisibility() == VISIBLE) {
            waresCart.getLocationInWindow(endPosition);
        }else {
            waresCartWhite.getLocationInWindow(endPosition);
        }
        nxHooldeView.setEndPosition(new Point(endPosition[0], endPosition[1]));
        nxHooldeView.startBeizerAnimation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (evaluateFragment != null) {
                showDetail();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showDetail() {
        if (evaluateFragment != null) {
            graphicDetailLayout.setVisibility(View.VISIBLE);
            getFragmentManager().beginTransaction().remove(evaluateFragment).commit();
            evaluateFragment = null;
            back.setText(R.string.back);
        }else {
            finish();
        }
    }
}
