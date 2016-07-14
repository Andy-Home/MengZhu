package com.andy.mengzhu.model;

import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public interface StatisticsModel {

    /**
     * 获取类目详情
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param listener
     * @param requestCode
     */
    void getCategory(int flag, OnDataRequestListener listener, int requestCode);

    /**
     * 获取支付类目详情
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param listener
     * @param requestCode
     */
    void getPayCategory(int flag, OnDataRequestListener listener, int requestCode);

    /**
     * 获取收支金额
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param listener
     * @param requestCode
     */
    void getBalanceOfPayments(int flag, OnDataRequestListener listener, int requestCode);
}
