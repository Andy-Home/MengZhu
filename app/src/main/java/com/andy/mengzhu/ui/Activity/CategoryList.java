package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andy.greendao.Category;
import com.andy.mengzhu.R;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.impl.CategoryPresenterImpl;
import com.andy.mengzhu.ui.adapter.ListAdapter;
import com.andy.mengzhu.ui.view.DataRequestView;
import com.andy.mengzhu.ui.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class CategoryList extends AppCompatActivity implements DataRequestView, Toolbar.OnMenuItemClickListener, View.OnClickListener {
    /**
     * 导航栏
     */
    private Toolbar mToolbar;

    /**
     * 用户选择收入或者支出项之后弹出该对话框，供用户填写新的类别项
     */
    private AlertDialog mAlertDialog;

    /**
     * 用户点击添加之后，弹出该对话框，由用户选择支出项与收入项
     */
    private AlertDialog chooseDialog;

    /**
     * 新建的类别项的名字
     */
    private EditText categoryName;

    /**
     * 对话框中的取消按钮
     */
    private Button cancel;

    /**
     * 对话框中的确定按钮
     */
    private Button determine;

    /**
     * 对话框中的收入项按钮
     */
    private TextView income;

    /**
     * 对话框中的支出项按钮
     */
    private TextView pay;

    /**
     * 类别项列表
     */
    private RecyclerView category_list;

    /**
     * category_list 的适配器
     */
    private ListAdapter mListAdapter = null;

    /**
     * 需要显示的 Category 类型的数据
     */
    private List<Category> categories = new ArrayList<>();

    /**
     * Presenter 层
     */
    private CategoryPresenter categoryPresenter = null;

    /**
     * 新建的Category类型的数据类型，0为支出、1为收入、2为收借款、3为转账
     */
    private int type = 0;

    /**
     * 获取Category类型数据请求的标志
     */
    private static final int GET_CATEGORY = 1;

    /**
     * 保存Category类型数据请求的标志
     */
    private static final int SAVE_CATEGORY = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findView();
        setToolBar();
        initData();
        setListener();
    }

    private void setListener() {
        mToolbar.setOnMenuItemClickListener(this);
    }

    private void findView() {
        category_list = (RecyclerView) findViewById(R.id.list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void setToolBar() {
        mToolbar.setTitle(R.string.menu_category);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        categoryPresenter = new CategoryPresenterImpl(this, this);
        categoryPresenter.getCategory(GET_CATEGORY);
    }

    private void initializeView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        category_list.setLayoutManager(mLayoutManager);
        category_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        category_list.setHasFixedSize(true);
        mListAdapter = new ListAdapter(categories, false);
        category_list.setAdapter(mListAdapter);
    }

    @Override
    public void showError(int requestCode) {

    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case GET_CATEGORY:
                categories.addAll((List<Category>) object);
                initializeView();
                break;
            case SAVE_CATEGORY:
                categories.clear();
                categories.addAll((List<Category>) object);
                mListAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_category:
                setChooseDialog();
                break;
        }
        return true;
    }

    /**
     * 自定对话框. 用户添加新的类别项，显示自定义的对话框
     */
    private void setCategoryDialog() {
        mAlertDialog = new AlertDialog.Builder(this).create();
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();
        window.setContentView(R.layout.dialog_add);
        window.setGravity(Gravity.CENTER);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        TextView tv_title = (TextView) window.findViewById(R.id.title);
        tv_title.setText(R.string.add_category_title);
        categoryName = (EditText) window.findViewById(R.id.name);
        cancel = (Button) window.findViewById(R.id.cancel);
        determine = (Button) window.findViewById(R.id.determine);
        cancel.setOnClickListener(this);
        determine.setOnClickListener(this);
    }

    private void setChooseDialog() {
        chooseDialog = new AlertDialog.Builder(this).create();
        chooseDialog.show();
        Window window = chooseDialog.getWindow();
        window.setContentView(R.layout.dialog_is_pay);
        window.setGravity(Gravity.CENTER);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        income = (TextView) window.findViewById(R.id.income);
        pay = (TextView) window.findViewById(R.id.pay);
        income.setOnClickListener(this);
        pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                mAlertDialog.cancel();
                break;

            case R.id.determine:
                Category category = new Category();
                category.setCategory_name(categoryName.getText().toString());
                category.setType(type);
                categoryPresenter.savaCategory(category, SAVE_CATEGORY);
                mAlertDialog.cancel();
                break;

            case R.id.pay:
                type = 0;
                chooseDialog.cancel();
                setCategoryDialog();
                break;

            case R.id.income:
                type = 1;
                chooseDialog.cancel();
                setCategoryDialog();
                break;
        }
    }
}
