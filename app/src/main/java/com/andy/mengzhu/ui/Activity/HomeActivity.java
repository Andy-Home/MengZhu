package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.common.BaseActivity;
import com.andy.mengzhu.ui.component.SwitchAndy;

public class HomeActivity extends BaseActivity implements View.OnClickListener{

    /**
     * 添加新账务按钮
     */
    private FloatingActionButton fab;

    /**
     * 账务显示，可以点击切换显示月和周
     */
    private SwitchAndy switchAndy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findView();
        setListener();


    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void setListener() {
        fab.setOnClickListener(this);
        switchAndy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:

                break;
        }
    }
}
