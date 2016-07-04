package com.andy.mengzhu.model;

import com.andy.greendao.Record;
import com.andy.mengzhu.presenter.OnDataRequestListener;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public interface RecordModel {

    /**
     * 保存新建的账务信息
     *
     * @param record    要保存的账务
     * @param listener
     */
    void saveRecord(Record record, OnDataRequestListener listener);
}
