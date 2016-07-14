package com.andy.mengzhu.presenter;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public interface StatisticsPresenter {

    /**
     * 获取类目详情
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param requestCode 请求数据的标志
     */
    void getCategory(int flag, int requestCode);

    /**
     * 获取支付类目详情
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param requestCode 请求数据的标志
     */
    void getPayCategory(int flag, int requestCode);

    /**
     * 获取账务收支，包括账务收入与支出
     *
     * @param flag        月或者周的标志，取值为 SwitchAndy.SWITCH_MONTH 或者 SwitchAndy.SWITCH_WEEK
     * @param requestCode 请求数据的标志
     */
    void getBalanceOfPayments(int flag, int requestCode);
}
