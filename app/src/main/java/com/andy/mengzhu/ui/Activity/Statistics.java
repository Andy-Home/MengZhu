package com.andy.mengzhu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.mengzhu.R;
import com.andy.mengzhu.model.entity.CategoryStatistics;
import com.andy.mengzhu.presenter.StatisticsPresenter;
import com.andy.mengzhu.presenter.impl.StatisticsPresenterImpl;
import com.andy.mengzhu.ui.component.Chart;
import com.andy.mengzhu.ui.component.SwitchAndy;
import com.andy.mengzhu.ui.view.DataRequestView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class Statistics extends Fragment implements DataRequestView, View.OnClickListener {

    /**
     * 圆形图表
     */
    private Chart mChart;

    /**
     * Presenter 层
     */
    private StatisticsPresenter statisticsPresenter;

    /**
     * 类目本周的显示数据
     */
    private List<CategoryStatistics> weekDatas = new ArrayList<>();

    /**
     * 类目当月的显示数据
     */
    private List<CategoryStatistics> monthDatas = new ArrayList<>();

    /**
     * 请求本周类目详细数据的标志
     */
    private final static int WEEK_CATEGORY = 1;

    /**
     * 请求当月类目详细数据的标志
     */
    private final static int MONTH_CATEGORY = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_statistics, container, false);
        findView(view);
        initData();
        setListener();
        initializeView();
        return view;
    }

    private void findView(View view) {
        mChart = (Chart) view.findViewById(R.id.chart);
    }

    private void initData() {
        statisticsPresenter = new StatisticsPresenterImpl(getActivity(), this);
        statisticsPresenter.getPayCategory(SwitchAndy.SWITCH_WEEK, WEEK_CATEGORY);
        statisticsPresenter.getPayCategory(SwitchAndy.SWITCH_MONTH, MONTH_CATEGORY);
    }

    private void setListener() {
        mChart.setOnClickListener(this);
    }

    private void initializeView() {
        mChart.setMonthStatistics(monthDatas);
        mChart.setWeekStatistics(weekDatas);
    }

    @Override
    public void setView(Object object, int requestCode) {
        switch (requestCode) {
            case WEEK_CATEGORY:
                weekDatas = (List<CategoryStatistics>) object;
                break;
            case MONTH_CATEGORY:
                monthDatas = (List<CategoryStatistics>) object;
                break;
        }
    }

    @Override
    public void showError(int requestCode) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chart:
                break;
        }
    }
}
