package com.andy.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "RECORD".
 */
public class Record {

    private Long id;
    private Double num;
    private java.util.Date date;
    private String desc;
    private Long category_id;
    private String category_name;
    private Long funds_id;
    private String funds_name;
    private Integer type;

    public Record() {
    }

    public Record(Long id) {
        this.id = id;
    }

    public Record(Long id, Double num, java.util.Date date, String desc, Long category_id, String category_name, Long funds_id, String funds_name, Integer type) {
        this.id = id;
        this.num = num;
        this.date = date;
        this.desc = desc;
        this.category_id = category_id;
        this.category_name = category_name;
        this.funds_id = funds_id;
        this.funds_name = funds_name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Long getFunds_id() {
        return funds_id;
    }

    public void setFunds_id(Long funds_id) {
        this.funds_id = funds_id;
    }

    public String getFunds_name() {
        return funds_name;
    }

    public void setFunds_name(String funds_name) {
        this.funds_name = funds_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
