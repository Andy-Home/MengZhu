package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.DaoSession;
import com.andy.greendao.Funds;
import com.andy.greendao.FundsDao;
import com.andy.greendao.Record;
import com.andy.greendao.RecordDao;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.presenter.OnDataRequestListener;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class RecordModelImpl implements RecordModel {
    private DaoSession daoSession;
    private RecordDao recordDao;
    private FundsDao fundsDao;
    private CategoryDao categoryDao;

    public RecordModelImpl(Activity activity){
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.recordDao = daoSession.getRecordDao();
        this.fundsDao = daoSession.getFundsDao();
        this.categoryDao = daoSession.getCategoryDao();
    }

    @Override
    public void saveRecord(Record record, OnDataRequestListener listener) {
        recordDao.insert(record);
    }

    @Override
    public void getRecord(int requestCode, OnDataRequestListener listener) {
        List<Record> result = new ArrayList<>();
        QueryBuilder recordQuery = recordDao.queryBuilder();
        recordQuery.orderAsc(RecordDao.Properties.Date);
        List<Record> recordList = recordQuery.list();
        //补充完整的 Record 信息，当 FundsID 与 CategoryID 为-1时，表示该类型数据已删除，取 Record 中 FundsName 与 CategoryName
        for (Record record : recordList) {
            if (!record.getFunds_id().equals(-1)) {
                QueryBuilder fundsQuery = fundsDao.queryBuilder();
                fundsQuery.where(FundsDao.Properties.Id.eq(record.getFunds_id()));
                List<Funds> funds = fundsQuery.list();
                record.setFunds_name(funds.get(0).getFunds_name());
            }
            if (!record.getCategory_id().equals(-1)) {
                QueryBuilder categoryQuery = categoryDao.queryBuilder();
                categoryQuery.where(CategoryDao.Properties.Id.eq(record.getCategory_id()));
                List<Category> category = categoryQuery.list();
                record.setCategory_name(category.get(0).getCategory_name());
            }
            result.add(record);
        }
        listener.onSuccess(result, requestCode);
    }

    @Override
    public void deleteRecord(Record record) {
        recordDao.delete(record);
    }

    @Override
    public void updateRecord(Record record) {
        recordDao.update(record);
    }
}
