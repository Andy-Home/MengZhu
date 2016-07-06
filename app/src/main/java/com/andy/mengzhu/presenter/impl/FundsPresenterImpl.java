package com.andy.mengzhu.presenter.impl;

import android.app.Activity;

import com.andy.greendao.Funds;
import com.andy.mengzhu.model.FundsModel;
import com.andy.mengzhu.model.impl.FundsModelImpl;
import com.andy.mengzhu.presenter.FundsPresenter;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class FundsPresenterImpl implements FundsPresenter, OnDataRequestListener {
    private FundsModel fundsModel;
    private DataRequestView view;

    public FundsPresenterImpl(Activity activity, DataRequestView view) {
        this.fundsModel = new FundsModelImpl(activity);
        this.view = view;
    }

    @Override
    public void getFunds(int requestCode) {
        fundsModel.getFunds(requestCode, this);
    }

    @Override
    public void savaFunds(Funds funds, int requestCode) {
        fundsModel.savaFunds(funds, requestCode, this);
    }

    @Override
    public void deleteFunds(Funds funds, int requestCode) {
        fundsModel.deleteFunds(funds, requestCode, this);
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
