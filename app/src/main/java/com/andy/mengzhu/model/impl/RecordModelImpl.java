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
import com.andy.mengzhu.app.util.DateUtil;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.model.entity.BalanceOfPayments;
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
            if (!record.getFunds_id().equals(Long.valueOf(-1))) {
                QueryBuilder fundsQuery = fundsDao.queryBuilder();
                fundsQuery.where(FundsDao.Properties.Id.eq(record.getFunds_id()));
                List<Funds> funds = fundsQuery.list();
                record.setFunds_name(funds.get(0).getFunds_name());
            }
            if (!record.getCategory_id().equals(Long.valueOf(-1))) {
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

    @Override
    public void update(final Object object) {
        List<Record> recordList = null;
        if (object instanceof Funds) {
            QueryBuilder recodQuery = recordDao.queryBuilder();
            recodQuery.where(RecordDao.Properties.Funds_id.eq(((Funds) object).getId()));
            recordList = recodQuery.list();
            String name = ((Funds) object).getFunds_name();
            for (int i = 0; i < recordList.size(); i++) {
                recordList.get(i).setFunds_id(Long.valueOf(-1));
                recordList.get(i).setFunds_name(name);
            }
            recordDao.insertOrReplaceInTx(recordList);
        } else if (object instanceof Category) {
            QueryBuilder recodQuery = recordDao.queryBuilder();
            recodQuery.where(RecordDao.Properties.Category_id.eq(((Category) object).getId()));
            recordList = recodQuery.list();
            String name = ((Category) object).getCategory_name();
            for (int i = 0; i < recordList.size(); i++) {
                recordList.get(i).setCategory_id(Long.valueOf(-1));
                recordList.get(i).setCategory_name(name);
            }
            recordDao.insertOrReplaceInTx(recordList);
        }
    }

    /**
     * 查询某一类别的账务记录的本周数据总和
     *
     * @param category
     * @return
     */
    public double findWeekSumBaseCategory(Category category) {
        QueryBuilder recordQuery = recordDao.queryBuilder();
        recordQuery.where(RecordDao.Properties.Category_id.eq(category.getId()));
        recordQuery.where(RecordDao.Properties.Date.between(DateUtil.getWeekStart(), DateUtil.getWeekEnd()));
        List<Record> recordList = recordQuery.list();
        double sum = 0.0;
        for (Record record : recordList) {
            sum += record.getNum();
        }
        return sum;
    }

    /**
     * 查询某一类别的账务记录的当月数据总和
     *
     * @param category
     * @return
     */
    public double findMonthSumBaseCategory(Category category) {
        QueryBuilder recordQuery = recordDao.queryBuilder();
        recordQuery.where(RecordDao.Properties.Category_id.eq(category.getId()));
        recordQuery.where(RecordDao.Properties.Date.between(DateUtil.getMonthStart(), DateUtil.getMonthEnd()));
        List<Record> recordList = recordQuery.list();
        double sum = 0.0;
        for (Record record : recordList) {
            sum += record.getNum();
        }
        return sum;
    }

    /**
     * 查询本周的收支总情况
     *
     * @return
     */
    public BalanceOfPayments findWeekBalanceOfPayments() {
        QueryBuilder recordQuery = recordDao.queryBuilder();
        recordQuery.where(RecordDao.Properties.Date.between(DateUtil.getWeekStart(), DateUtil.getWeekEnd()));
        List<Record> recordList = recordQuery.list();
        double paySum = 0.0;
        double incomeSum = 0.0;
        for (Record record : recordList) {
            if (record.getType() == 0) {
                paySum += record.getNum();
            } else if (record.getType() == 1) {
                incomeSum += record.getNum();
            }
        }

        BalanceOfPayments result = new BalanceOfPayments();
        result.setPay(paySum);
        result.setIncome(incomeSum);
        result.setBalance(incomeSum - paySum);
        return result;
    }

    /**
     * 查询本月的收支总情况
     *
     * @return
     */
    public BalanceOfPayments findMonthBalanceOfPayments() {
        QueryBuilder recordQuery = recordDao.queryBuilder();
        recordQuery.where(RecordDao.Properties.Date.between(DateUtil.getMonthStart(), DateUtil.getMonthEnd()));
        List<Record> recordList = recordQuery.list();
        double paySum = 0.0;
        double incomeSum = 0.0;
        for (Record record : recordList) {
            if (record.getType() == 0) {
                paySum += record.getNum();
            } else if (record.getType() == 1) {
                incomeSum += record.getNum();
            }
        }

        BalanceOfPayments result = new BalanceOfPayments();
        result.setPay(paySum);
        result.setIncome(incomeSum);
        result.setBalance(incomeSum - paySum);
        return result;
    }
}
