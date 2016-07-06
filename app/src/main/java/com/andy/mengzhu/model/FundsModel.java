package com.andy.mengzhu.model;

import com.andy.greendao.Funds;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public interface FundsModel {
    /**
     * 获取所有的 Funds
     *
     * @param requestCode
     */
    void getFunds(int requestCode, OnDataRequestListener listener);

    /**
     * 保存新建的 Funds
     *
     * @param funds
     */
    void savaFunds(Funds funds);

    /**
     * 删除选中的 Funds
     *
     * @param funds
     */
    void deleteFunds(Funds funds);
}