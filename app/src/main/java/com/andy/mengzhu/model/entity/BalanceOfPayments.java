package com.andy.mengzhu.model.entity;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class BalanceOfPayments {

    /**
     * 收入值
     */
    private double income;

    /**
     * 支出值
     */
    private double pay;

    /**
     * 收入差值
     */
    private double balance;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
