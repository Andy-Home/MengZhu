package com.andy.mengzhu.presenter.impl;

import android.app.Activity;

import com.andy.greendao.Record;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.model.impl.RecordModelImpl;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.presenter.RecordPresenter;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class RecordPresenterImpl  implements RecordPresenter, OnDataRequestListener{
    private RecordModel recordModel;
    private DataRequestView view;

    public RecordPresenterImpl(Activity activity, DataRequestView view) {
        recordModel = new RecordModelImpl(activity);
        this.view = view;
    }

    @Override
    public void saveRecord(Record record) {
        recordModel.saveRecord(record, this);
    }

    @Override
    public void getRecord(int requestCode) {
        recordModel.getRecord(requestCode, this);
    }

    @Override
    public void deleteRecord(Record record) {
        recordModel.deleteRecord(record);
    }

    @Override
    public void updateRecord(Record record) {
        recordModel.updateRecord(record);
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
