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
    private int requestCode = -1;
    private RecordModel recordModel;
    DataRequestView view;

    public RecordPresenterImpl(Activity activity){
        recordModel = new RecordModelImpl(activity);
    }

    @Override
    public void saveRecord(Record record) {
        recordModel.saveRecord(record, this);
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
