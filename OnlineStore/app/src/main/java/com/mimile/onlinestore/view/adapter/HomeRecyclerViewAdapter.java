package com.mimile.onlinestore.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.HomeCampaign;
import com.mimile.onlinestore.util.enums.ECampaign;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by caidongdong on 2016/12/1 10:54
 * email : mircaidong@163.com
 */
public class HomeRecyclerViewAdapter extends BaseMultiItemQuickAdapter<HomeCampaign>{

    private Context context;


    public HomeRecyclerViewAdapter(List<HomeCampaign> data, Context context) {
        super(data);
        this.context = context;
        addItemType(ECampaign.ONE_CENTER.getValue(), R.layout.rv_home_campaign_one_item);
        addItemType(ECampaign.TWO_CENTER.getValue(), R.layout.rv_home_campaign_two_item);
        addItemType(ECampaign.THREE_LEFT.getValue(), R.layout.rv_home_campaign_three_left_item);
        addItemType(ECampaign.THREE_RIGHT.getValue(), R.layout.rv_home_campaign_three_right_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeCampaign homeCampaign) {
        switch (holder.getItemViewType()) {
            case 0:
                loadImgs((ImageView) holder.getView(R.id.img_home_campaign_one),homeCampaign.getCpOne().getImgUrl());
                break;
            case 1:
                loadImgs((ImageView) holder.getView(R.id.img_home_campaign_two_left),homeCampaign.getCpOne().getImgUrl());
                loadImgs((ImageView) holder.getView(R.id.img_home_campaign_two_right),homeCampaign.getCpTwo().getImgUrl());
                break;
            case 2:
                loadImgs((ImageView) holder.getView(R.id.imgview_big),homeCampaign.getCpOne().getImgUrl());
                loadImgs((ImageView) holder.getView(R.id.imgview_small_top),homeCampaign.getCpTwo().getImgUrl());
                loadImgs((ImageView) holder.getView(R.id.imgview_small_bottom),homeCampaign.getCpThree().getImgUrl());
                ((TextView)holder.getView(R.id.text_title)).setText(homeCampaign.getTitle());
                break;
            case 3:
                loadImgs((ImageView) holder.getView(R.id.imgview_big),homeCampaign.getCpOne().getImgUrl());
                loadImgs((ImageView) holder.getView(R.id.imgview_small_top),homeCampaign.getCpTwo().getImgUrl());
                loadImgs((ImageView) holder.getView(R.id.imgview_small_bottom),homeCampaign.getCpThree().getImgUrl());
                ((TextView)holder.getView(R.id.text_title)).setText(homeCampaign.getTitle());
                break;
            default:
                break;
        }
    }

    /**
     * 异步加载图片
     * @param imageView
     * @param imgUrl
     */
    private void loadImgs(ImageView imageView,String imgUrl) {
        //TODO 替换站位图和错误图
        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.mipmap.food)
                .error(R.mipmap.movie)
                .into(imageView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 0;
                }
            });
        }
    }
}
