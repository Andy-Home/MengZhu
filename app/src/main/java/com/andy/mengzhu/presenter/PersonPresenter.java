package com.andy.mengzhu.presenter;

import com.andy.greendao.Person;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public interface PersonPresenter {
    /**
     * 获取所有的 Funds
     *
     * @param requestCode
     */
    void getPerson(int requestCode);

    /**
     * 保存新建的 Funds
     *
     * @param person
     */
    void savaPerson(Person person, int requestCode);

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
     */
    void updatePerson(Person person, int requestCode);
}
