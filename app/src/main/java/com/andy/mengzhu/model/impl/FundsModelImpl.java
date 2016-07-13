package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.DaoSession;
import com.andy.greendao.Funds;
import com.andy.greendao.FundsDao;
import com.andy.greendao.Record;
import com.andy.greendao.RecordDao;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.FundsModel;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class FundsModelImpl implements FundsModel {
    private DaoSession daoSession;

    private FundsDao fundsDao = null;

    private RecordModel recordModel = null;

    public FundsModelImpl(Activity activity) {
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.fundsDao = daoSession.getFundsDao();
        this.recordModel = new RecordModelImpl(activity);
    }

    @Override
    public void getFunds(int requestCode, OnDataRequestListener listener) {
        QueryBuilder queryBuilder = fundsDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void savaFunds(Funds funds, int requestCode, OnDataRequestListener listener) {
        fundsDao.insert(funds);
        QueryBuilder queryBuilder = fundsDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void deleteFunds(Funds funds) {
        recordModel.update(funds);
        fundsDao.delete(funds);
    }

    @Override
    public void updateFunds(Funds funds, int requestCode, OnDataRequestListener listener) {
        fundsDao.update(funds);
        QueryBuilder queryBuilder = fundsDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }
}
