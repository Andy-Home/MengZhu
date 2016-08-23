package com.andy.mengzhu.model;

import com.andy.greendao.Funds;
import com.andy.greendao.Person;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public interface PersonModel {
    /**
     * 获取所有的 Funds
     *
     * @param requestCode
     */
    void getPerson(int requestCode, OnDataRequestListener listener);

    /**
     * 保存新建的 Funds
     *
     * @param person
     */
    void savaPerson(Person person, int requestCode, OnDataRequestListener listener);

    /**
     * 删除选中的 Funds
     *
     * @param person
     */
    void deletePerson(Person person);

    /**
     * 更新 Funds
     *
     * @param person
     * @param requestCode
     * @param listener
     */
    void updatePerson(Person person, int requestCode, OnDataRequestListener listener);
}
