package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.DaoSession;
import com.andy.greendao.Record;
import com.andy.greendao.RecordDao;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class RecordModelImpl implements RecordModel {
    private DaoSession daoSession;
    private RecordDao recordDao;

    public RecordModelImpl(Activity activity){
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.recordDao = daoSession.getRecordDao();
    }

    @Override
    public void saveRecord(Record record, OnDataRequestListener listener) {
        recordDao.insert(record);
    }

    @Override
    public void getRecord(int requestCode, OnDataRequestListener listener) {
        QueryBuilder queryBuilder = recordDao.queryBuilder();
        queryBuilder.orderAsc(RecordDao.Properties.Date);
        listener.onSuccess(queryBuilder.list(), requestCode);
    }
}
