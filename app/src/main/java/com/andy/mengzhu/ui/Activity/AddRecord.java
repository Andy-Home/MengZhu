package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.common.BaseActivity;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class AddRecord extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    /**
     * 选择类别的控件
     */
    private Spinner categoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findView();
        setListener();
        initializeView();
    }

    private void findView() {
        categoryView = (Spinner) findViewById(R.id.finance_category);
    }

    private void setListener() {
        categoryView.setOnItemSelectedListener(this);
    }

    private void initializeView() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_name, android.R.layout.simple_spinner_item);
        //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到spinner中去
        categoryView.setAdapter(adapter);
        categoryView.setVisibility(View.VISIBLE);//设置默认显示
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
