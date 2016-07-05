package com.andy.mengzhu.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class TabPageAdapter extends FragmentPagerAdapter {
    List<Fragment> data = null;
    List<String> mTitle = null;

    public TabPageAdapter(FragmentManager fm, List<Fragment> data, List<String> mTitle) {
        super(fm);
        this.data = data;
        this.mTitle = mTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
