package com.andy.mengzhu.model;

import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public interface CategoryModel {

    /**
     * 获取类目详情
     *
     * @param flag  月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param listener
     * @param requestCode
     */
    void getCategory(int flag, OnDataRequestListener listener, int requestCode);

    /**
     * 获取所有的 Category
     *
     * @param requestCode
     */
    void getCategory(int requestCode, OnDataRequestListener listener);

    /**
     * 保存新建的 Category
     *
     * @param category
     */
    void savaCategory(Category category, int requestCode, OnDataRequestListener listener);

    /**
     * 删除选中的 Category
     *
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * 更新 Category
     *
     * @param category
     * @param requestCode
     * @param listener
     */
    void updateCategory(Category category, int requestCode, OnDataRequestListener listener);
}
