package com.andy.mengzhu.presenter.impl;

import android.app.Activity;

import com.andy.greendao.Category;
import com.andy.mengzhu.model.CategoryModel;
import com.andy.mengzhu.model.impl.CategoryModelImpl;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.ui.view.DataRequestView;

/**
 * Created by andy on 16-7-6.
 */
public class CategoryPresenterImpl implements CategoryPresenter, OnDataRequestListener {
    private CategoryModel categoryModel;
    private DataRequestView view;

    public CategoryPresenterImpl(Activity activity, DataRequestView view) {
        this.categoryModel = new CategoryModelImpl(activity);
        this.view = view;
    }

    @Override
    public void getCategory(int requestCode) {
        categoryModel.getCategory(requestCode, this);
    }

    @Override
    public void savaCategory(Category category, int requestCode) {
        categoryModel.savaCategory(category, requestCode, this);
    }

    @Override
    public void deleteCategory(Category category, int requestCode) {
        categoryModel.deleteCategory(category, requestCode, this);
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
