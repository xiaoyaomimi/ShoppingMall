package com.mimile.onlinestore.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.mimile.base.view.fragment.ContentFragment;
import com.mimile.base.view.zxing.android.CaptureActivity;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.HeaderMenu;
import com.mimile.onlinestore.entity.HomeCampaign;
import com.mimile.onlinestore.model.IUserModel;
import com.mimile.onlinestore.model.UserModelImp;
import com.mimile.onlinestore.util.CommonCallback;
import com.mimile.onlinestore.util.GlideImageLoader;
import com.mimile.onlinestore.view.activity.SearchWaresActivity;
import com.mimile.onlinestore.view.adapter.GridViewMenuAdapter;
import com.mimile.onlinestore.view.adapter.HomeRecyclerViewAdapter;
import com.mimile.onlinestore.view.adapter.decortion.CardViewtemDecortion;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caidongdong on 2016/11/28 15:59
 * email : mircaidong@163.com
 */
public class HomeFragment extends ContentFragment implements View.OnClickListener, CommonCallback<List<HomeCampaign>>, SwipeRefreshLayout.OnRefreshListener{
    private List<String> images;
    private List<String> titles;
    private Banner banner;
    private GridView gridView;
    private GridViewMenuAdapter gridViewAdapter;
    private List<HeaderMenu> headerMenuList;
    private RelativeLayout searchBar;
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;
    private IUserModel userModel;
    private List<HomeCampaign> homeCampaignList;
    private RecyclerViewHeader header;
    private SwipeRefreshLayout refreshLayout;
    private int headerHeight;
    private ImageView scan;
    private TextView searchTv;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 10011;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_home, null);
        banner = (Banner) view.findViewById(R.id.banner);
        gridView = (GridView) view.findViewById(R.id.home_main_menu);
        searchBar = (RelativeLayout) view.findViewById(R.id.rl_home_serach_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home_commend_list);
        header = (RecyclerViewHeader) view.findViewById(R.id.rv_home_header);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rv_home_refresh_layout);
        scan = (ImageView) view.findViewById(R.id.img_home_sacn);
        searchTv = (TextView) view.findViewById(R.id.home_fragment_search_tv);
        scan.setOnClickListener(this);
        searchTv.setOnClickListener(this);
        return view;
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        //初始化图片轮播
        titles = new ArrayList<>();
        images = new ArrayList<>();
        titles.add("广告");
        titles.add("轮播");
        titles.add("图片");
        images.add("http://www.pp3.cn/uploads/201504/2015040202.jpg");
        images.add("http://photo.enterdesk.com/2010-11-2/enterdesk.com-5CA99D47E472CBF6C9FC93C4B07709B8.jpg");
        images.add("http://img.tuku.cn/file_big/201504/4a2850e750db42d086adb8b9719643e1.jpg");
        //初始化gridview菜单
        gridView.setNumColumns(4);
        headerMenuList = new ArrayList<HeaderMenu>();
        headerMenuList.add(new HeaderMenu());
        headerMenuList.add(new HeaderMenu());
        headerMenuList.add(new HeaderMenu());
        headerMenuList.add(new HeaderMenu());
        gridViewAdapter = new GridViewMenuAdapter(getActivity(), headerMenuList);
        gridView.setAdapter(gridViewAdapter);
        //菜单点击事件监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "你好" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //seekbar模拟首页滚动条改变搜索栏透明度
//        searchBar.setAlpha(0f);
        initBanner();
        //初始化首页广告数据
        homeCampaignList = new ArrayList<>();
        adapter = new HomeRecyclerViewAdapter(homeCampaignList, getActivity());
        recyclerView.addItemDecoration(new CardViewtemDecortion());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                      int topRowVerticalPosition =
//                              (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
//                      refreshLayout.setEnabled(topRowVerticalPosition >= 0);
                int distance = getScrolledDistance();
                if (headerHeight == 0)
                    headerHeight = distance;
                if (distance == headerHeight) {
                    searchBar.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    searchBar.setBackgroundColor(Color.parseColor("#FF4081"));
//                            searchBar.setAlpha(((float)distance - headerHeight) / headerHeight);
                }
            }
        });
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setProgressViewOffset(true, 200, 300);
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorAccentPress);
        header.attachTo(recyclerView);
        userModel = new UserModelImp(this);
        test();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.banner_main_default:
//
//                break;
            case R.id.img_home_sacn:
                int hasWriteContactsPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                    ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CONTACTS);
                } else {
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
                break;
            case R.id.home_fragment_search_tv:
                Intent sintent = new Intent(getActivity(), SearchWaresActivity.class);
                startActivity(sintent);
                break;
            default:
                break;
        }
    }

    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置banner监听器
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 1:
                        Toast.makeText(getActivity(), "hhha", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void test() {
        userModel.test();
    }

    @Override
    public void onSuccess(List<HomeCampaign> campaigns) {
//        if (banners != null && !banners.isEmpty()) {;
//            images.clear();
//            titles.clear();
//            for (HomeBanner banner : banners) {
//                images.add(banner.getImgUrl());
//                titles.add(banner.getName());
//            }
//            initBanner();
//        }
        adapter.setNewData(campaigns);
    }

    @Override
    public void onFailure(String error) {

    }

    /**
     * 获取recyclerview已滑动高度
     *
     * @return
     */
    private int getScrolledDistance() {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View firstVisibleItem = recyclerView.getChildAt(0);
        int firstItemPosition = manager.findFirstVisibleItemPosition();
        int itemHeight = firstVisibleItem.getHeight();
        int firstItemBottom = manager.getDecoratedBottom(firstVisibleItem);
        return (firstItemPosition + 1) * itemHeight - firstItemBottom;
    }

    @Override
    public void onRefresh() {

        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE_SCAN:
                if (data == null)
                    break;
                String str = data.getStringExtra("codedContent");
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
