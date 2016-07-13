package com.andy.mengzhu.model.entity;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class CategoryStatistics {

    /**
     * 对应类目的ID
     */
    private Long id;

    /**
     * 类目名
     */
    private String categoryName;

    /**
     * 该类目的金额
     */
    private double categoryNum = 0.00;

    /**
     * 类目的类型
     */
    private int type = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
