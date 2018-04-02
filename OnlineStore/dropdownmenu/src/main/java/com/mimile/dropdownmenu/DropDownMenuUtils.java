package com.mimile.dropdownmenu;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mimile.dropdownmenu.adapter.DropDownAdapter;
import com.mimile.dropdownmenu.adapter.GridDropDownAdapter;
import com.mimile.dropdownmenu.adapter.ListIconDropDownAdapter;
import com.mimile.dropdownmenu.adapter.ListTextDropDownAdapter;

import java.util.ArrayList;
import java.util.List;


public class DropDownMenuUtils {
    private static final int TEXT_LIST = 1;
    private static final int ICON_LIST = 2;
    private static final int GRID_VIEW = 3;
    //打钩的图片
    public static int imgResource;

    public static void addTextList(Context context,final DropDownMenu dropDownMenu, final OnMenuClickListener clickListener, final List<String>... namess){
        addTabList(context,TEXT_LIST,dropDownMenu,clickListener,namess);
    }

    public static void addIconList(Context context,final DropDownMenu dropDownMenu, final OnMenuClickListener clickListener, final List<String>... namess){
        addTabList(context,ICON_LIST,dropDownMenu,clickListener,namess);
    }

    public static void addGridList(Context context,final DropDownMenu dropDownMenu, final OnMenuClickListener clickListener, final List<String>... namess){
        addTabList(context,GRID_VIEW,dropDownMenu,clickListener,namess);
    }

    private static void addTabList(Context context, int whichAdapter,final DropDownMenu dropDownMenu, final OnMenuClickListener clickListener, final List<String>... namess){
        List<String> heads = new ArrayList<>();
        List<View> popupViews = new ArrayList<>();
        for(int i=0;i<namess.length;i++){
            List<String> names = namess[i];
            heads.add(names.get(0));
            popupViews.add(getListView(context,whichAdapter,i,dropDownMenu,names,clickListener));
        }
        dropDownMenu.setDropDownMenu(heads, popupViews);
    }

    /**
     * @param context
     * @param viewPosition listview在layout中的位置
     * @param dropDownMenu
     * @param names listview需要的字符串列表
     * @param clickListener
     * @return
     */
    private static AbsListView getListView(Context context, int whichAdapter,final int viewPosition, final DropDownMenu dropDownMenu, final List<String> names, final OnMenuClickListener clickListener){
        DropDownAdapter adapter = null;
        if(whichAdapter == TEXT_LIST){
            adapter = new ListTextDropDownAdapter(context, names);
        }else if(whichAdapter == ICON_LIST){
            adapter = new ListIconDropDownAdapter(context, names);
            ((ListIconDropDownAdapter)adapter).setImgResource(imgResource);
        }else if(whichAdapter == GRID_VIEW){
            adapter = new GridDropDownAdapter(context,names);
        }
        final DropDownAdapter finalAdapter = adapter;

        AbsListView listView = null;
        if(whichAdapter == GRID_VIEW){
            listView = (AbsListView) View.inflate(context,R.layout.grid_drop_down,null);
        }else {
            listView = new ListView(context);
            ((ListView)listView).setDividerHeight(0);
        }
        listView.setAdapter(finalAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(clickListener != null)
                    clickListener.onMenuClick(viewPosition,position);
                finalAdapter.setCheckItem(position);
                dropDownMenu.setTabText(names.get(position));
                dropDownMenu.closeMenu();
            }
        });
        return listView;
    }

    /**
     * 使用这个方法时弹窗的最外层必须是AbsListView
     * 重现显示选择的值的状态，不触发被选择事件,选择事件外部单独写
     * @param dropDownMenu
     * @param viewPosition
     * @param itemPosition
     */
    public static void setSelection(DropDownMenu dropDownMenu,int viewPosition, int itemPosition){
        AbsListView absListView = (ListView) dropDownMenu.getPopupViewAtPosition(viewPosition);
        DropDownAdapter adapter = (DropDownAdapter) absListView.getAdapter();
        String name = (String) adapter.getItem(itemPosition);
        dropDownMenu.setTabText(viewPosition,name);
        adapter.setCheckItem(itemPosition);
    }

    public  interface OnMenuClickListener{
        void onMenuClick(int viewPosition, int itemPosition);
    }
}
