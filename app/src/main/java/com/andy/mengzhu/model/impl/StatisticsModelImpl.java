package com.andy.mengzhu.model.impl;

import android.app.Activity;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.DaoSession;
import com.andy.greendao.FundsDao;
import com.andy.greendao.Record;
import com.andy.greendao.RecordDao;
import com.andy.mengzhu.app.AndyApplication;
import com.andy.mengzhu.model.CategoryModel;
import com.andy.mengzhu.model.FundsModel;
import com.andy.mengzhu.model.RecordModel;
import com.andy.mengzhu.model.StatisticsModel;
import com.andy.mengzhu.model.entity.BalanceOfPayments;
import com.andy.mengzhu.model.entity.CategoryStatistics;
import com.andy.mengzhu.presenter.OnDataRequestListener;
import com.andy.mengzhu.ui.component.SwitchAndy;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class StatisticsModelImpl implements StatisticsModel {
    private DaoSession daoSession;

    private RecordModelImpl recordModel;

    private FundsModelImpl fundsModel;

    private CategoryModelImpl categoryModel;

    public StatisticsModelImpl(Activity activity) {
        AndyApplication application = (AndyApplication) activity.getApplication();
        this.daoSession = application.getDaoSession();
        this.recordModel = new RecordModelImpl(activity);
        this.fundsModel = new FundsModelImpl(activity);
        this.categoryModel = new CategoryModelImpl(activity);
    }

    @Override
    public void getCategory(int flag, OnDataRequestListener listener, int requestCode) {
        List<Category> categoryList = categoryModel.getAllCategory();
        List<CategoryStatistics> categoryStatisticsList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryStatistics cs = new CategoryStatistics();
            cs.setId(category.getId());
            cs.setCategoryName(category.getCategory_name());
            double num = 0.0;
            if (flag == SwitchAndy.SWITCH_MONTH) {
                num = recordModel.findMonthSumBaseCategory(category);
            } else if (flag == SwitchAndy.SWITCH_WEEK) {
                num = recordModel.findWeekSumBaseCategory(category);
            }
            cs.setCategoryNum(num);
            cs.setType(category.getType());
            categoryStatisticsList.add(cs);
        }
        listener.onSuccess(categoryStatisticsList, requestCode);
    }

    @Override
    public void getBalanceOfPayments(int flag, OnDataRequestListener listener, int requestCode) {
        BalanceOfPayments balanceOfPayments = null;
        if (flag == SwitchAndy.SWITCH_MONTH) {
            balanceOfPayments = recordModel.findMonthBalanceOfPayments();
        } else if (flag == SwitchAndy.SWITCH_WEEK) {
            balanceOfPayments = recordModel.findWeekBalanceOfPayments();
        }
        listener.onSuccess(balanceOfPayments, requestCode);
    }

}
