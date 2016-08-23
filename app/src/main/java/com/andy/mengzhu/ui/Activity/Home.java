package com.andy.mengzhu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.mengzhu.R;
import com.andy.mengzhu.model.entity.BalanceOfPayments;
import com.andy.mengzhu.model.entity.CategoryStatistics;
import com.andy.mengzhu.presenter.StatisticsPresenter;
import com.andy.mengzhu.presenter.impl.StatisticsPresenterImpl;
import com.andy.mengzhu.ui.adapter.CategoryAdapter;
import com.andy.mengzhu.ui.component.SwitchAndy;
import com.andy.mengzhu.ui.view.DividerItemDecoration;
import com.andy.mengzhu.ui.view.DataRequestView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class Home extends Fragment implements View.OnClickListener, DataRequestView {

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

    private StatisticsPresenter statisticsPresenter;

    /**
     * 请求本周类目详细数据的标志
     */
    private final static int WEEK_CATEGORY = 1;

    /**
     * 请求本周收支数据的标志
     */
    private final static int WEEK_BALANCEOFPAYMENTS = 2;

    /**
     * 请求当月类目详细数据的标志
     */
    private final static int MONTH_CATEGORY = 3;

    /**
     * 请求当月收支数据的标志
     */
    private final static int MONTH_BALANCEOFPAYMENTS = 4;

    /**
     * 类目本周的显示数据
     */
    private List<CategoryStatistics> weekDatas = new ArrayList<>();

    /**
     * 类目当月的显示数据
     */
    private List<CategoryStatistics> monthDatas = new ArrayList<>();

    /**
     * 类目的显示数据
     */
    private List<CategoryStatistics> datas = new ArrayList<>();

    /**
     * 收支本周的显示数据
     */
    private BalanceOfPayments weekBOP = null;

    /**
     * 收支当月的显示数据
     */
    private BalanceOfPayments monthBOP = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, container, false);

        findView(view);
        initData();
        setListener();
        initializeView();
        return view;
    }



    private void findView(View view) {
        switchAndy = (SwitchAndy) view.findViewById(R.id.balance);
        categoryView = (RecyclerView) view.findViewById(R.id.list_category);

        incomeNum = (TextView) view.findViewById(R.id.income_num);
        incomeText = (TextView) view.findViewById(R.id.income_text);
        payNum = (TextView) view.findViewById(R.id.pay_num);
        payText = (TextView) view.findViewById(R.id.pay_text);
    }

    private void initData() {
        statisticsPresenter = new StatisticsPresenterImpl(getActivity(), this);
        statisticsPresenter.getCategory(SwitchAndy.SWITCH_WEEK, WEEK_CATEGORY);
        statisticsPresenter.getCategory(SwitchAndy.SWITCH_MONTH, MONTH_CATEGORY);
        statisticsPresenter.getBalanceOfPayments(SwitchAndy.SWITCH_WEEK, WEEK_BALANCEOFPAYMENTS);
        statisticsPresenter.getBalanceOfPayments(SwitchAndy.SWITCH_MONTH, MONTH_BALANCEOFPAYMENTS);
    }

    private void setListener() {
        switchAndy.setOnClickListener(this);
    }

    private void initializeView() {
        switchAndy.setDisplayWeekNum(weekBOP.getBalance());
        switchAndy.setDisplayMonthNum(monthBOP.getBalance());
        incomeNum.setText(monthBOP.getIncome() + "");
        payNum.setText(monthBOP.getPay() + "");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        categoryView.setLayoutManager(mLayoutManager);
        categoryView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        categoryView.setHasFixedSize(true);
        mAdapter = new CategoryAdapter(datas);
        categoryView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.balance:
                if (switchAndy.getFlag() == SwitchAndy.SWITCH_MONTH) {
                    incomeNum.setText(monthBOP.getIncome() + "");
                    payNum.setText(monthBOP.getPay() + "");
                    incomeText.setText(R.string.current_month_income);
                    payText.setText(R.string.current_month_pay);
                    datas.clear();
                    datas.addAll(monthDatas);
                    mAdapter.notifyDataSetChanged();
                } else if (switchAndy.getFlag() == SwitchAndy.SWITCH_WEEK) {
                    incomeNum.setText(weekBOP.getIncome() + "");
                    payNum.setText(weekBOP.getPay() + "");
                    incomeText.setText(R.string.current_week_income);
                    payText.setText(R.string.current_week_pay);
                    datas.clear();
                    datas.addAll(weekDatas);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void showError(int requestCode) {
        Toast.makeText(getActivity(), R.string.request_wrong, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case WEEK_CATEGORY:
                weekDatas = (List<CategoryStatistics>) object;
                break;
            case MONTH_CATEGORY:
                monthDatas = (List<CategoryStatistics>) object;
                datas.clear();
                datas.addAll(monthDatas);
                break;
            case WEEK_BALANCEOFPAYMENTS:
                weekBOP = (BalanceOfPayments) object;
                break;
            case MONTH_BALANCEOFPAYMENTS:
                monthBOP = (BalanceOfPayments) object;
                break;
        }
    }
}
