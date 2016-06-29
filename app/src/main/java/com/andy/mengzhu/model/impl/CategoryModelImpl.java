package com.andy.mengzhu.model.impl;

import com.andy.mengzhu.model.CategoryModel;
import com.andy.mengzhu.model.entity.Category;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class CategoryModelImpl implements CategoryModel {

    private List<Category> categoryList = new ArrayList<>();

    public CategoryModelImpl(){
        Category category0 = new Category();
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
        categoryList.add(category2);
    }

    @Override
    public void getCategory(int flag, OnDataRequestListener listener, int requestCode) {
        listener.onSuccess(categoryList,requestCode);
    }
}
