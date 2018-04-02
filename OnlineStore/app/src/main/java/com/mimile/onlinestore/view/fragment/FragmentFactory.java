package com.mimile.onlinestore.view.fragment;

/**
 * Created by caidongdong on 2016/11/28 16:45
 * email : mircaidong@163.com
 */

import android.support.v4.app.Fragment;
import android.util.Log;

import com.mimile.base.view.fragment.ContentFragment;

import java.util.HashMap;

/**
 * Created by caidongdong on 2015/11/19.
 */
public class FragmentFactory {
    public static final int TAB_HOME = 0;
    public static final int TAB_KIND = 1;
    public static final int TAB_FIND = 2;
    public static final int TAB_CART = 3;
    public static final int TAB_ME = 4;

    //缓存所有的Fragment对象
    public static HashMap<Integer,ContentFragment> fragmentHashMap = new HashMap<Integer, ContentFragment>();

    public static Fragment createFragment(int position) {
        ContentFragment mFragment = fragmentHashMap.get(position);
        if (mFragment == null) {
            switch (position){
                case TAB_HOME:
                    mFragment = new HomeFragment();
                    break;
                case TAB_KIND:
                    mFragment = new KindFragment();
                    break;
                case TAB_FIND:
                    mFragment = new FindFragment();
                    break;
                case TAB_CART:
                    mFragment = new CartFragment();
                    break;
                case TAB_ME:
                    mFragment = new MeFragment();
                    break;
            }
            fragmentHashMap.put(position,mFragment);
        }
        return mFragment;
    }
    public static Fragment getFragment(int postion) {
        if (fragmentHashMap.get(postion) == null) {
            return createFragment(postion);
        }else {
            Log.e("TAG","--------------fragment not null ------------");
            return fragmentHashMap.get(postion);
        }
    }
}
