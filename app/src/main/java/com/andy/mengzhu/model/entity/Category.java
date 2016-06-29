package com.andy.mengzhu.model.entity;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class Category {

    /**
     * 类目名
     */
    private String categoryName;

    /**
     * 该类目的金额
     */
    private double categoryNum = 0.00;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(double categoryNum) {
        this.categoryNum = categoryNum;
    }
}
