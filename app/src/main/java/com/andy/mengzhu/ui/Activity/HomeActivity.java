package com.andy.mengzhu.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andy.mengzhu.R;
import com.andy.mengzhu.model.entity.BalanceOfPayments;
import com.andy.mengzhu.model.entity.Category;
import com.andy.mengzhu.presenter.HomePresenter;
import com.andy.mengzhu.presenter.impl.HomePresenterImpl;
import com.andy.mengzhu.ui.adapter.CategoryAdapter;
import com.andy.mengzhu.ui.common.BaseActivity;
import com.andy.mengzhu.ui.component.SwitchAndy;
import com.andy.mengzhu.ui.view.InitializeView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener, InitializeView {

    /**
     * 添加新账务按钮
     */
    private FloatingActionButton fab;

    /**
     * 账务显示，可以点击切换显示月和周
     */
    private SwitchAndy switchAndy;

    /**
     * 显示类目
     */
    private RecyclerView categoryView;

    /**
     * categoryView 的适配器
     */
    private CategoryAdapter mAdapter;

    /**
     * 显示收入金额
     */
    private TextView incomeNum;

    /**
     * 显示收入为本月还是本周
     */
    private TextView incomeText;

    /**
     * 显示支出金额
     */
    private TextView payNum;

    /**
     * 显示支出为本月还是本周
     */
    private TextView payText;

    private HomePresenter homePresenter;
    /**
     * 请求类目详细数据的标志
     */
    private final static int REQUEST_CATEGORY = 1;

    /**
     * 请求收支数据的标志
     */
    private final static int REQUEST_BALANCEOFPAYMENTS = 2;

    /**
     * 类目显示的数据
     */
    private List<Category> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findView();
        setListener();
        initializeView();
    }

    private void findView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        switchAndy = (SwitchAndy) findViewById(R.id.balance);
        categoryView = (RecyclerView) findViewById(R.id.list_category);

        incomeNum = (TextView) findViewById(R.id.income_num);
        incomeText = (TextView) findViewById(R.id.income_text);
        payNum = (TextView) findViewById(R.id.pay_num);
        payText = (TextView) findViewById(R.id.pay_text);
    }

    private void setListener() {
        fab.setOnClickListener(this);
        switchAndy.setOnClickListener(this);
    }

    private void initializeView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        categoryView.setLayoutManager(mLayoutManager);
        categoryView.addItemDecoration(new RecyclerView.ItemDecoration() {
        });
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        categoryView.setHasFixedSize(true);
        //创建并设置Adapter
        mAdapter = new CategoryAdapter(datas);
        categoryView.setAdapter(mAdapter);

        homePresenter = new HomePresenterImpl(this);
        homePresenter.getCategory(SwitchAndy.SWITCH_MONTH, REQUEST_CATEGORY);
        homePresenter.getBalanceOfPayments(SwitchAndy.SWITCH_MONTH, REQUEST_BALANCEOFPAYMENTS);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                break;

            case R.id.balance:

                break;
        }
    }

    @Override
    public void showError(int requestCode) {

    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode){
            case REQUEST_CATEGORY:
                datas.clear();
                datas.addAll((List<Category>) object);
                mAdapter.notifyDataSetChanged();
                break;
            case REQUEST_BALANCEOFPAYMENTS:
                BalanceOfPayments balanceOfPayments = (BalanceOfPayments) object;
                incomeNum.setText(balanceOfPayments.getIncome()+"");
                payNum.setText(balanceOfPayments.getPay()+"");

                if(switchAndy.getFlag() == SwitchAndy.SWITCH_MONTH){
                    incomeText.setText(R.string.current_month_income);
                    payText.setText(R.string.current_month_pay);
                    switchAndy.setDisplayMonthNum(balanceOfPayments.getBalance());
                }else{
                    incomeText.setText(R.string.current_week_income);
                    payText.setText(R.string.current_week_pay);
                    switchAndy.setDisplayWeekNum(balanceOfPayments.getBalance());
                }
                break;
        }
    }
}
