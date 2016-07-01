package com.andy.mengzhu.model.entity;

import java.sql.Date;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class Record {
    /**
     * 账务的金钱数
     */
    private double num;

    /**
     * 发生该账务的日期
     */
    private Date date;

    /**
     * 账务详情
     */
    private String desc;

    /**
     * 类目名字
     */
    private String category;

    /**
     * 资金项，即扣款项与收入项
     */
    private String funds;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
