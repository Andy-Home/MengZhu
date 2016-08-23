package com.andy.mengzhu.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.andy.mengzhu.R;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        jump();
    }

    /**
     * 延迟跳转
     */
    private void jump() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Start.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
