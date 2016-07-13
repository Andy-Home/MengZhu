package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.DaoSession;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.CategoryModel;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class CategoryModelImpl implements CategoryModel {
    private DaoSession daoSession;

    private CategoryDao categoryDao = null;

    private List<Category> categoryList = new ArrayList<>();

    private RecordModel recordModel = null;

    public CategoryModelImpl(Activity activity) {
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.categoryDao = daoSession.getCategoryDao();
        this.recordModel = new RecordModelImpl(activity);
    }

    public CategoryModelImpl() {

    }
    @Override
    public void getCategory(int flag, OnDataRequestListener listener, int requestCode) {
        listener.onSuccess(categoryList,requestCode);
    }

    @Override
    public void getCategory(int requestCode, OnDataRequestListener listener) {
        listener.onSuccess(getAllCategory(), requestCode);
    }

    @Override
    public void savaCategory(Category category, int requestCode, OnDataRequestListener listener) {
        categoryDao.insert(category);
        listener.onSuccess(getAllCategory(), requestCode);
    }

    @Override
    public void deleteCategory(Category category) {
        recordModel.update(category);
        categoryDao.delete(category);
    }

    @Override
    public void updateCategory(Category category, int requestCode, OnDataRequestListener listener) {
        categoryDao.update(category);
        listener.onSuccess(getAllCategory(), requestCode);
    }

    public List<Category> getAllCategory() {
        QueryBuilder queryBuilder = categoryDao.queryBuilder();
        return queryBuilder.list();
    }

}
