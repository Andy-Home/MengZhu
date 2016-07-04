package com.andy.mengzhu.presenter.impl;

import com.andy.mengzhu.model.BalanceOfPaymentsModel;
import com.andy.mengzhu.model.CategoryModel;
import com.andy.mengzhu.model.impl.BalanceOfPaymentsModelImpl;
import com.andy.mengzhu.model.impl.CategoryModelImpl;
import com.andy.mengzhu.presenter.HomePresenter;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class HomePresenterImpl implements HomePresenter, OnDataRequestListener {
    private CategoryModel categoryModel;
    private BalanceOfPaymentsModel balanceOfPaymentsModel;
    private DataRequestView view;


    public HomePresenterImpl(DataRequestView view){
        categoryModel = new CategoryModelImpl();
        balanceOfPaymentsModel = new BalanceOfPaymentsModelImpl();
        this.view = view;
    }

    @Override
    public void getCategory(int flag, int requestCode) {
        categoryModel.getCategory(flag, this, requestCode);
    }

    @Override
    public void getBalanceOfPayments(int flag, int requestCode) {
        balanceOfPaymentsModel.getBalanceOfPayments(flag,this,requestCode);
    }

    @Override
    public void onSuccess(Object object, int requestCode) {
        view.setView(object,requestCode);
    }

    @Override
    public void onError(int requestCode) {
        view.showError(requestCode);
    }
}
