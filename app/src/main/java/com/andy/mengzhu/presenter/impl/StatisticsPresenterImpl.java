package com.andy.mengzhu.presenter.impl;

import android.app.Activity;

import com.andy.mengzhu.model.StatisticsModel;
import com.andy.mengzhu.model.impl.StatisticsModelImpl;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.presenter.StatisticsPresenter;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class StatisticsPresenterImpl implements StatisticsPresenter, OnDataRequestListener {
    private StatisticsModel statisticsModel;

    private DataRequestView view;

    public StatisticsPresenterImpl(Activity activity, DataRequestView view) {
        this.statisticsModel = new StatisticsModelImpl(activity);
        this.view = view;
    }

    @Override
    public void getCategory(int flag, int requestCode) {
        statisticsModel.getCategory(flag, this, requestCode);
    }

    @Override
    public void getPayCategory(int flag, int requestCode) {
        statisticsModel.getPayCategory(flag, this, requestCode);
    }

    @Override
    public void getBalanceOfPayments(int flag, int requestCode) {
        statisticsModel.getBalanceOfPayments(flag, this, requestCode);
    }

    @Override
    public void onSuccess(Object object, int requestCode) {
        view.setView(object, requestCode);
    }

    @Override
    public void onError(int requestCode) {
        view.showError(requestCode);
    }
}
