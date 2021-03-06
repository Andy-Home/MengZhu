package com.andy.mengzhu.presenter;


import com.andy.greendao.Record;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public interface RecordPresenter {

    /**
     * 保存新建的记录
     *
     * @param record    要保存的记录
     */
    void saveRecord(Record record);

    /**
     * 获取所有的 Record
     *
     * @param requestCode
     */
    void getRecord(int requestCode);

    /**
     * 删除对应的 Record
     *
     * @param record
     */
    void deleteRecord(Record record);

    /**
     * 更新Record
     *
     * @param record
     */
    void updateRecord(Record record);
}
