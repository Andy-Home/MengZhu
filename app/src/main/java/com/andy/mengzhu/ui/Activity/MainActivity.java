package com.andy.mengzhu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.adapter.TabPageAdapter;
import com.andy.mengzhu.ui.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 *
 * Created by Administrator on 2016/7/5 0005.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 添加新账务按钮
     */
    private FloatingActionButton fab;

    /**
     * 导航栏
     */
    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;

    /**
     * 菜单中的资金项
     */
    private RelativeLayout menu_funds;

    /**
     * 菜单中的类别项
     */
    private RelativeLayout menu_category;

    /**
     * 菜单中的借/还款人
     */
    private RelativeLayout menu_person;

    /**
     * 菜单中的 关于
     */
    private RelativeLayout menu_about;

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
        setToolBar();
        setListener();
        initData();
        initializeView();
    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        menu_funds = (RelativeLayout) findViewById(R.id.menu_funds);
        menu_category = (RelativeLayout) findViewById(R.id.menu_category);
        menu_person = (RelativeLayout) findViewById(R.id.menu_person);
        menu_about = (RelativeLayout) findViewById(R.id.menu_about);
    }

    private void setToolBar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListener() {
        fab.setOnClickListener(this);
        menu_funds.setOnClickListener(this);
        menu_category.setOnClickListener(this);
        menu_person.setOnClickListener(this);
        menu_about.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolbar.setTitle(R.string.title_statistics);
                        break;
                    case 1:
                        toolbar.setTitle(R.string.app_name);
                        break;
                    case 2:
                        toolbar.setTitle(R.string.title_detail);
                        break;
                    default:
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mFragments.add(new Statistics());
        mFragments.add(new Home());
        mFragments.add(new RecordList());

        mTitle.add(getString(R.string.statistics));
        mTitle.add(getString(R.string.home));
        mTitle.add(getString(R.string.detail));
    }


    private void initializeView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        //向TabLayout中添加标签
        for (String title : mTitle) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        //显示 ViewPager
        mTabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), mFragments, mTitle);
        mViewPager.setAdapter(mTabPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(1);
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

            case R.id.menu_person:
                Intent intent3 = new Intent(this, PersonList.class);
                startActivity(intent3);
                break;

            case R.id.menu_about:
                Intent intent4 = new Intent(this, About.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mTabPageAdapter.notifyDataSetChanged();
    }

}
