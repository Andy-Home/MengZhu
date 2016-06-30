package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andy.mengzhu.R;
import com.andy.mengzhu.ui.adapter.CategoryAdapter;
import com.andy.mengzhu.ui.adapter.DateAdapter;
import com.andy.mengzhu.ui.common.BaseActivity;
import com.andy.mengzhu.ui.view.DividerItemDecoration;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class AddRecord extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    /**
     * 选择类别的控件
     */
    private Spinner categoryView;

    /**
     * 日期选择控件
     */
    private RecyclerView dateView;

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
        dateView = (RecyclerView) findViewById(R.id.finance_date);
    }

    private void setListener() {
        categoryView.setOnItemSelectedListener(this);
    }

    private void initializeView() {
        //配置类别选择器
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryView.setAdapter(adapter);

        //配置日期选择器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dateView.setLayoutManager(mLayoutManager);
        dateView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        dateView.setHasFixedSize(true);
        dateView.setAdapter(new DateAdapter());
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
