package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.DaoSession;
import com.andy.greendao.FundsDao;
import com.andy.greendao.Person;
import com.andy.greendao.PersonDao;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.PersonModel;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class PersonModelImpl implements PersonModel {
    private DaoSession daoSession;

    private PersonDao personDao = null;

    private RecordModel recordModel = null;

    public PersonModelImpl(Activity activity) {
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.personDao = daoSession.getPersonDao();
        this.recordModel = new RecordModelImpl(activity);
    }

    @Override
    public void getPerson(int requestCode, OnDataRequestListener listener) {
        QueryBuilder queryBuilder = personDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void savaPerson(Person person, int requestCode, OnDataRequestListener listener) {
        personDao.insert(person);
        QueryBuilder queryBuilder = personDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void deletePerson(Person person) {
        //TODO 当借贷人删除时更新账务记录
        recordModel.update(person);
        personDao.delete(person);
    }

    @Override
    public void updatePerson(Person person, int requestCode, OnDataRequestListener listener) {
        personDao.update(person);
        QueryBuilder queryBuilder = personDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }
}
