package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.adapter.TabPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabPageAdapter mTabPageAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initData();
        initializeView();
    }

    private void findView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

    private void initData() {
        mFragments.add(new HomeActivity());

        mTitle.add("Home");
    }

    private void initializeView() {
        for (String title : mTitle) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        mTabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mViewPager.setAdapter(mTabPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
