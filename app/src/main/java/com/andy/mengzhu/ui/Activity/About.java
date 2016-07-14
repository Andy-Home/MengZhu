package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.common.BaseActivity;

/**
 * Created by andy on 16-7-14.
 */
public class About extends BaseActivity {
    /**
     * 导航栏
     */
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findView();
        setToolBar();
    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setToolBar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
