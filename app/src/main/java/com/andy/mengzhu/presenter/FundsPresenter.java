package com.andy.mengzhu.presenter;

import com.andy.greendao.Funds;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public interface FundsPresenter {

    /**
     * 获取所有的 Funds
     *
     * @param requestCode
     */
    void getFunds(int requestCode);

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
