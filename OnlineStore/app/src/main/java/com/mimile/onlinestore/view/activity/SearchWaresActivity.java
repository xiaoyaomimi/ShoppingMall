package com.mimile.onlinestore.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.mimile.base.view.activity.CommonBaseActivity;
import com.mimile.base.view.tagview.FlowLayout;
import com.mimile.base.view.tagview.TagAdapter;
import com.mimile.base.view.tagview.TagFlowLayout;
import com.mimile.onlinestore.R;
import com.mimile.onlinestore.entity.Wares;
import com.mimile.onlinestore.util.KeyBoardUtil;
import com.mimile.onlinestore.util.OptionSelectedCallBack;
import com.mimile.onlinestore.view.adapter.SearchWaresAdapter;
import com.mimile.onlinestore.view.adapter.SearchWaresGridAdapter;
import com.mimile.onlinestore.view.adapter.decortion.SpacesItemDecoration;
import com.mimile.onlinestore.view.customview.SearchKindPopwin;
import com.mimile.onlinestore.view.customview.WaresFilterDialog;
import com.mimile.onlinestore.view.customview.WaresSortSelectPopwin;

import java.util.ArrayList;
import java.util.List;

public class SearchWaresActivity extends CommonBaseActivity implements View.OnClickListener, OptionSelectedCallBack, TextView.OnEditorActionListener,CompoundButton.OnCheckedChangeListener {
    private TextView searchKind;
    private TagFlowLayout historySearch;
    private TagFlowLayout distorySearch;
    private List<String> list;
    private List<Wares> waresList;
    private CheckBox showOrHideDis;
    private TextView textView;
    private EditText editText;
    private RadioButton sortKey;
    private RelativeLayout rl;
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private CheckBox layoutStyle;
    private SpacesItemDecoration decoration;
    private TextView waresFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wares);
        searchKind = (TextView) findViewById(R.id.activity_search_wares_kind_tv);
        historySearch = (TagFlowLayout) findViewById(R.id.activity_search_wares_flowlayout);
        distorySearch = (TagFlowLayout) findViewById(R.id.activity_search_wares_discory_flowlayout);
        showOrHideDis = (CheckBox) findViewById(R.id.activity_search_wares_check_box);
        textView = (TextView) findViewById(R.id.activity_search_wares_ban_note_tv);
        editText = (EditText) findViewById(R.id.activity_search_wares_edit_text);
        sortKey = (RadioButton) findViewById(R.id.activity_search_wares_sort_key_cb);
        rl = (RelativeLayout) findViewById(R.id.activity_search_wares_opt_rl);
        recyclerView = (RecyclerView) findViewById(R.id.activity_search_wares_rv);
        frameLayout = (FrameLayout) findViewById(R.id.activity_search_wares_frame_layout);
        linearLayout = (LinearLayout) findViewById(R.id.activity_search_wares_history_ll);
        layoutStyle = (CheckBox) findViewById(R.id.activity_search_wares_layout_type_cb);
        waresFilter = (TextView) findViewById(R.id.activity_search_wares_filter_tv);
        historySearch.setMaxSelectCount(1);
        distorySearch.setMaxSelectCount(1);
        searchKind.setOnClickListener(this);
        sortKey.setOnClickListener(this);
        editText.setFocusable(true);
        editText.setOnEditorActionListener(this);
        final LayoutInflater mInflater = LayoutInflater.from(this);
        list = new ArrayList<>();
        list.add("全部(15000)");
        list.add("有图(500)");
        list.add("追加(100)");
        list.add("全部(1500)");
        list.add("有图(500)");
        list.add("追加(100)");
        TagAdapter tagAdapter = new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView tv = (TextView) mInflater.inflate(R.layout.search_wares_flow_layout_history_item,
                        historySearch, false);
                tv.setText(str);
                return tv;
            }
        };
        historySearch.setAdapter(tagAdapter);
        distorySearch.setAdapter(tagAdapter);
        waresFilter.setOnClickListener(this);
        showOrHideDis.setOnCheckedChangeListener(this);
        layoutStyle.setOnCheckedChangeListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        waresList = new ArrayList<>();
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        waresList.add(new Wares());
        recyclerView.setAdapter(new SearchWaresAdapter(waresList));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(SearchWaresActivity.this,WaresDetialActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_search_wares_kind_tv:
                SearchKindPopwin popwin = new SearchKindPopwin(this, this);
                popwin.showAsDropDown(searchKind, 0, 10);
                break;
            case R.id.activity_search_wares_sort_key_cb:
                try {
                    WaresSortSelectPopwin pwin = new WaresSortSelectPopwin(this, sortKey.getText().toString(), this);
                    //改变activity透明度
                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.alpha = 0.7f;
                    getWindow().setAttributes(params);
                    pwin.showAsDropDown(rl, 0, 2);
                    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_drop_up_orange_24dp);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    sortKey.setCompoundDrawables(null, null, drawable, null);
                    pwin.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            WindowManager.LayoutParams params = getWindow().getAttributes();
                            params.alpha = 1f;
                            getWindow().setAttributes(params);
                            Drawable drawable = ContextCompat.getDrawable(SearchWaresActivity.this, R.drawable.radion_button_foucs_selector);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            sortKey.setCompoundDrawables(null, null, drawable, null);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.activity_search_wares_filter_tv:
                WaresFilterDialog dialog = new WaresFilterDialog(this);
                dialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void cancle() {

    }

    @Override
    public void confirm(String str) {
        if (str.length() > 2) {
            sortKey.setText(str);
        } else {
            searchKind.setText(str);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (frameLayout.getVisibility() != View.VISIBLE) {
                linearLayout.setVisibility(View.INVISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
//                if(getWindow().getAttributes().softInputMode== WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
                KeyBoardUtil.hideKeyboard(this);
            }
        }
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.activity_search_wares_layout_type_cb:
                if (isChecked) {
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    SearchWaresGridAdapter gridAdapter = new SearchWaresGridAdapter(waresList);
                    if (decoration == null) {
                        decoration = new SpacesItemDecoration(3);
                        recyclerView.addItemDecoration(decoration);
                    }
                    recyclerView.setAdapter(gridAdapter);
                }else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    if (decoration != null) {
                        recyclerView.removeItemDecoration(decoration);
                        decoration = null;
                    }
                    recyclerView.setAdapter(new SearchWaresAdapter(waresList));
                }
                break;
            case R.id.activity_search_wares_check_box:
                if (isChecked) {
                    distorySearch.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                    distorySearch.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
