package com.andy.mengzhu.model;

import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public interface BalanceOfPaymentsModel {

    /**
     * 获取收支金额
     *
     * @param flag  月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param listener
     * @param requestCode
     */
    void getBalanceOfPayments(int flag, OnDataRequestListener listener, int requestCode);
}
