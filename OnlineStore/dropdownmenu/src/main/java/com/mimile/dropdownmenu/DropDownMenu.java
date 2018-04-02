package com.mimile.dropdownmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 使用时，在这个布局最外层的view添加android:layout_below="@id/dropDownMenu_underLine"这一行代码。
 *
 */
public class DropDownMenu extends RelativeLayout {

    //底层参照物控件，作为其他控件布局时候的相对参照
    private LinearLayout tabMenuViewButtom;
    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器，包含popupMenuViews，maskView
    private View containerView;
    //弹出菜单父布局
    private FrameLayout popupMenuViews;
    //遮罩半透明View，点击可关闭DropDownMenu
    private View maskView;
    //tabMenuView里面选中的tab位置，-1表示未选中
    private int current_tab_position = -1;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //tab选中颜色
    private int textSelectedColor = 0xff890c85;
    //tab未选中颜色
    private int textUnselectedColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //tab字体大小
    private int menuTextSize = 14;

    //tab选中图标
    private int menuSelectedIcon;
    //tab未选中图标
    private int menuUnselectedIcon;
    //菜单背景色
    private int menuBackgroundColor = 0xffffffff;
    //菜单下面的分割线颜色
    private int underlineColor = 0xffcccccc;
    //菜单的箭头是否靠右边
    private boolean menuIconToRight = false;
    /**************使用layout文件夹的XML布局的menu***************************/
    /**
     * labmenu的layout的id，layout里面只能有一个下拉的item,并且使用setDropDownMenu方法后才能使用context初始化labMenuLayout里面的view
     * 或者使用setOnInitMenuUI方法
     */
    private int labMenuLayoutId ;
    //是否使用layout文件夹的XML布局menu
    private boolean isUseXmlLayout = false;
    //使用layout文件夹的XML布局menu的textview
    private TextView xmlTextView;
    //初始化labMenuLayout里面的view
    private onInitMenuUI onInitMenuUI;
    /********************************************************************/

    public DropDownMenu(Context context) {
        super(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //为DropDownMenu添加自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        underlineColor = a.getColor(R.styleable.DropDownMenu_ddunderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.DropDownMenu_dddividerColor, dividerColor);
        textSelectedColor = a.getColor(R.styleable.DropDownMenu_ddtextSelectedColor, textSelectedColor);
        textUnselectedColor = a.getColor(R.styleable.DropDownMenu_ddtextUnselectedColor, textUnselectedColor);
        menuBackgroundColor = a.getColor(R.styleable.DropDownMenu_ddmenuBackgroundColor, menuBackgroundColor);
        maskColor = a.getColor(R.styleable.DropDownMenu_ddmaskColor, maskColor);
        menuTextSize = a.getDimensionPixelSize(R.styleable.DropDownMenu_ddmenuTextSize, menuTextSize);
        menuSelectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuSelectedIcon, menuSelectedIcon);
        menuUnselectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuUnselectedIcon, menuUnselectedIcon);
        menuIconToRight = a.getBoolean(R.styleable.DropDownMenu_ddmenuIconToRight, menuIconToRight);
        labMenuLayoutId = a.getResourceId(R.styleable.DropDownMenu_ddMenuLayout, 0);
        if(labMenuLayoutId != 0){
            isUseXmlLayout = true;
        }
        a.recycle();

        //初始化tabMenuView并添加到tabMenuView的参照物
        tabMenuViewButtom = new LinearLayout(context);
        tabMenuViewButtom.setId(R.id.dropDownMenu_tabMenuView);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(tabMenuViewButtom,0);

        //为tabMenuView添加下划线
        View underLine = new View(getContext());
        underLine.setId(R.id.dropDownMenu_underLine);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        lp.addRule(RelativeLayout.BELOW, R.id.dropDownMenu_tabMenuView);
        underLine.setLayoutParams(lp);
        underLine.setBackgroundColor(underlineColor);
        addView(underLine, 1);
    }

    /**
     * 初始化DropDownMenu
     *
     * @param tabTexts
     * @param popupViews
     */
    public void setDropDownMenu(@NonNull List<String> tabTexts, @NonNull List<View> popupViews) {
        if (tabTexts.size() != popupViews.size()) {
            throw new IllegalArgumentException("params not match, tabTexts.size() should be equal popupViews.size()");
        }

        LayoutParams plp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        plp.addRule(RelativeLayout.BELOW, R.id.dropDownMenu_underLine);

        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        maskView.setVisibility(GONE);
        maskView.setLayoutParams(plp);
        addView(maskView);

        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setVisibility(GONE);
        popupMenuViews.setLayoutParams(plp);
        addView(popupMenuViews);

        for (int i = 0; i < popupViews.size(); i++) {
            if (popupViews.get(i) == null) {
               continue;
            }
            popupViews.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            popupMenuViews.addView(popupViews.get(i), i);
        }

        //初始化tabMenuView并添加到tabMenuView，为了能tabMenuView使位于顶层，所以最后添加tabMenuView
        tabMenuView = new LinearLayout(getContext());
        tabMenuView.setId(R.id.dropDownMenu_tabMenuView);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(LinearLayout.HORIZONTAL);
        tabMenuView.setBackgroundColor(menuBackgroundColor);
        tabMenuView.setLayoutParams(params);
        addView(tabMenuView);

        if(isUseXmlLayout){
            tabMenuView.addView(getXmlTabMenuView(tabTexts.get(0)));
        }
        else {
            for (int i = 0; i < tabTexts.size(); i++) {
                addTab(tabTexts, i);
            }
        }

        //为了使tabMenuViewButtom和tabMenuView的高度一样
        LayoutParams tparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,tabMenuView.getHeight());
        tabMenuViewButtom.setLayoutParams(tparams);
    }

    /**
     * 参考tab_menu_view.xml写一个layout，textview的id名字必须一样
     * @param tabName
     * @return
     */
    private View getXmlTabMenuView(String tabName){
        View tabView = View.inflate(getContext(),labMenuLayoutId,null);
        if(tabView == null) {
            throw new IllegalArgumentException("labMenuLayoutId should not be 0,you must set a layout reference tab_menu_view.xml");
        }
        xmlTextView = (TextView) tabView.findViewById(R.id.tv_dropDownMenu_tab);
        xmlTextView.setText(tabName + "  ");
        xmlTextView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(menuUnselectedIcon), null);
        xmlTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(xmlTextView);
            }
        });
        if(onInitMenuUI != null)
            onInitMenuUI.initUI(tabView);
        return tabView;
    }



    private void addTab(@NonNull List<String> tabTexts, int i) {
        final TextView tab = new TextView(getContext());
        LinearLayout menuItenmView = new LinearLayout(getContext());
        menuItenmView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        menuItenmView.setGravity(Gravity.CENTER);
        menuItenmView.setOrientation(LinearLayout.HORIZONTAL);
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        if(menuIconToRight)
            tab.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        else
            tab.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tab.setTextColor(textUnselectedColor);
        tab.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(menuUnselectedIcon), null);
        tab.setText(tabTexts.get(i) + "  ");
        tab.setPadding(dpTpPx(5), dpTpPx(8), dpTpPx(5), dpTpPx(8));
        menuItenmView.addView(tab, 0);
        //添加点击事件
        menuItenmView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(tab);
            }
        });
        tabMenuView.addView(menuItenmView);
        //添加分割线
        if (i < tabTexts.size() - 1) {
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(dpTpPx(0.5f), ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }

    /**
     * 改变tab文字
     *
     * @param text
     */
    public void setTabText(String text) {
        setTabText(current_tab_position,text);
    }

    public void setTabText(int tab_position,String text) {
        if (tab_position != -1) {
            getTabMenuTextView(tab_position).setText(text+"  ");
        }
    }

//    public void setTabClickable(boolean clickable) {
//        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
//            tabMenuView.getChildAt(i).setClickable(clickable);
//        }
//    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (current_tab_position != -1) {
            TextView tab = getTabMenuTextView(current_tab_position);
            //FIXME 展开列表时如果是用layout的menu，则menu不需要显示被选择的颜色
            if(!isUseXmlLayout)
                tab.setTextColor(textUnselectedColor);
            tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(menuUnselectedIcon), null);
            popupMenuViews.setVisibility(View.GONE);
            popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            maskView.setVisibility(GONE);
            maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            current_tab_position = -1;
        }

    }

    /**
     * DropDownMenu是否处于可见状态
     *
     * @return
     */
    public boolean isShowing() {
        return current_tab_position != -1;
    }

    /**
     * 切换菜单
     *
     * @param target
     */
    private void switchMenu(View target) {
        System.out.println(current_tab_position);
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            TextView tab = getTabMenuTextView(i);
            if (target == tab) {
                if (current_tab_position == i) {
                    closeMenu();
                } else {
                    if (current_tab_position == -1) {
                        popupMenuViews.setVisibility(View.VISIBLE);
                        popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
                        maskView.setVisibility(VISIBLE);
                        maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
                        View view = popupMenuViews.getChildAt(i / 2);
                        popupMenuViews.getChildAt(i / 2).setVisibility(View.VISIBLE);
                    } else {
                        popupMenuViews.getChildAt(i / 2).setVisibility(View.VISIBLE);
                    }
                    current_tab_position = i;
                    //FIXME 展开列表时如果是用layout的menu，则menu不需要显示被选择的颜色
                    if(!isUseXmlLayout)
                        tab.setTextColor(textSelectedColor);
                    tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(menuSelectedIcon), null);
                }
            } else {
                //FIXME 展开列表时如果是用layout的menu，则menu不需要显示被选择的颜色
                if(!isUseXmlLayout)
                    tab.setTextColor(textUnselectedColor);
                tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(menuUnselectedIcon), null);
                popupMenuViews.getChildAt(i / 2).setVisibility(View.GONE);
            }
        }
    }

    private TextView getTabMenuTextView(int position){
        if(isUseXmlLayout)
            return xmlTextView;
        else
            return (TextView) ((ViewGroup)tabMenuView.getChildAt(position)).getChildAt(0);
    }

    /**
     * 获取弹出的view
     * @param position
     * @return
     */
    public View getPopupViewAtPosition(int position){
        if(position < popupMenuViews.getChildCount()){
            return popupMenuViews.getChildAt(position);
        }
        return null;
    }

    public int dpTpPx(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }

    /**
     * 当使用外部layout的menu时使用这个初始化View，否则必须在setDropDownMenu调用完后初始化
     * @param onInitMenuUI
     */
    public void setOnInitMenuUI(onInitMenuUI onInitMenuUI){
        this.onInitMenuUI = onInitMenuUI;
    }

    public interface onInitMenuUI{
        void initUI(View rootView);
    }
}
