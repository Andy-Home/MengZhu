package com.andy.mengzhu.presenter;

import com.andy.greendao.Category;

/**
 * Created by andy on 16-7-6.
 */
public interface CategoryPresenter {

    /**
     * 获取所有的 Category
     *
     * @param requestCode
     */
    void getCategory(int requestCode);

    /**
     * 保存新建的 Category
     *
     * @param category
     */
    void savaCategory(Category category, int requestCode);

    /**
     * 删除选中的 Category
     *
     * @param category
     */
    void deleteCategory(Category category, int requestCode);
}
