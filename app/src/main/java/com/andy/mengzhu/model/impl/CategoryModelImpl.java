package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.DaoSession;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.CategoryModel;
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

    public CategoryModelImpl(){
        /*Category category0 = new Category();
        category0.setCategoryName("交通");
        category0.setCategoryNum(100.00);
        categoryList.add(category0);

        Category category1 = new Category();
        category1.setCategoryName("主餐");
        category1.setCategoryNum(1000.00);
        categoryList.add(category1);

        Category category2 = new Category();
        category2.setCategoryName("水果");
        category2.setCategoryNum(500.00);
        categoryList.add(category2);*/
    }

    public CategoryModelImpl(Activity activity) {
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.categoryDao = daoSession.getCategoryDao();
    }

    @Override
    public void getCategory(int flag, OnDataRequestListener listener, int requestCode) {
        listener.onSuccess(categoryList,requestCode);
    }

    @Override
    public void getCategory(int requestCode, OnDataRequestListener listener) {
        QueryBuilder queryBuilder = categoryDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void savaCategory(Category category, int requestCode, OnDataRequestListener listener) {
        categoryDao.insert(category);
        QueryBuilder queryBuilder = categoryDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }

    @Override
    public void deleteCategory(Category category, int requestCode, OnDataRequestListener listener) {
        categoryDao.delete(category);
        QueryBuilder queryBuilder = categoryDao.queryBuilder();
        listener.onSuccess(queryBuilder.list(), requestCode);
    }


}
