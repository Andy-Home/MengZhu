package com.andy.mengzhu.presenter.impl;

import android.app.Activity;

import com.andy.greendao.Person;
import com.andy.mengzhu.model.FundsModel;
import com.andy.mengzhu.model.PersonModel;
import com.andy.mengzhu.model.impl.FundsModelImpl;
import com.andy.mengzhu.model.impl.PersonModelImpl;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.presenter.PersonPresenter;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class PersonPresenterImpl implements PersonPresenter, OnDataRequestListener {
    private PersonModel personModel;
    private DataRequestView view;

    public PersonPresenterImpl(Activity activity, DataRequestView view) {
        this.personModel = new PersonModelImpl(activity);
        this.view = view;
    }

    @Override
    public void getPerson(int requestCode) {
        personModel.getPerson(requestCode, this);
    }

    @Override
    public void savaPerson(Person person, int requestCode) {
        personModel.savaPerson(person, requestCode, this);
    }

    @Override
    public void deletePerson(Person person) {
        personModel.deletePerson(person);
    }

    @Override
    public void updatePerson(Person person, int requestCode) {
        personModel.updatePerson(person, requestCode, this);
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
