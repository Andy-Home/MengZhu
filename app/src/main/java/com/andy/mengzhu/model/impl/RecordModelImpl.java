package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.DaoSession;
import com.andy.greendao.Record;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class RecordModelImpl implements RecordModel {
    private DaoSession daoSession;

    public RecordModelImpl(Activity activity){
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
    }
    @Override
    public void saveRecord(Record record, OnDataRequestListener listener) {
        daoSession.getRecordDao().insert(record);
    }
}
