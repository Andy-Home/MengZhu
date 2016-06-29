package com.andy.mengzhu.model.impl;

import com.andy.mengzhu.model.BalanceOfPaymentsModel;
import com.andy.mengzhu.model.entity.BalanceOfPayments;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class BalanceOfPaymentsModelImpl implements BalanceOfPaymentsModel {
    private BalanceOfPayments balanceOfPayments = new BalanceOfPayments();

    public BalanceOfPaymentsModelImpl(){
        balanceOfPayments.setIncome(100.00);
        balanceOfPayments.setPay(50.00);
        balanceOfPayments.setBalance(balanceOfPayments.getIncome() - balanceOfPayments.getPay());
    }

    @Override
    public void getBalanceOfPayments(int flag, OnDataRequestListener listener, int requestCode) {
        listener.onSuccess(balanceOfPayments,requestCode);
    }
}
