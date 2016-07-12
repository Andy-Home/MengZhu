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

    /**
     * 获取所有的 Record
     *
     * @param requestCode
     */
    void getRecord(int requestCode, OnDataRequestListener listener);

    /**
     * 删除对应的 Record
     *
     * @param record
     */
    void deleteRecord(Record record);

    /**
     * 更新 Record
     *
     * @param record
     */
    void updateRecord(Record record);
}
