package com.andy.mengzhu.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.adapter.TabPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 *
 * Created by Administrator on 2016/7/5 0005.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 添加新账务按钮
     */
    private FloatingActionButton fab;

    /**
     * 菜单中的资金项
     */
    private RelativeLayout menu_funds;

    /**
     * 菜单中的类别项
     */
    private RelativeLayout menu_category;

    /**
     * 标签布局
     */
    private TabLayout mTabLayout;

    /**
     * 用来切换各种 Fragment
     */
    private ViewPager mViewPager;

    private TabPageAdapter mTabPageAdapter;

    /**
     * 保存需要显示的 Fragment
     */
    private List<Fragment> mFragments = new ArrayList<>();

    /**
     * 根据 mFragment 的顺序保存相应的标题
     */
    private List<String> mTitle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setListener();
        initData();
        initializeView();
    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        menu_funds = (RelativeLayout) findViewById(R.id.menu_funds);
        menu_category = (RelativeLayout) findViewById(R.id.menu_category);
    }

    private void setListener() {
        fab.setOnClickListener(this);
        menu_funds.setOnClickListener(this);
        menu_category.setOnClickListener(this);
    }

    private void initData() {
        mFragments.add(new Home());
        mFragments.add(new RecordList());

        mTitle.add("Home");
        mTitle.add("Detail");
    }


    private void initializeView() {
        //向TabLayout中添加标签
        for (String title : mTitle) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        //显示 ViewPager
        mTabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mViewPager.setAdapter(mTabPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, AddRecord.class);
                startActivity(intent);
                break;

            case R.id.menu_funds:
                Intent intent1 = new Intent(this, FundsList.class);
                startActivity(intent1);
                break;

            case R.id.menu_category:
                Intent intent2 = new Intent(this, CategoryList.class);
                startActivity(intent2);
                break;
        }
    }
}
