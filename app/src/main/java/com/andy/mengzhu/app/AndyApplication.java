package com.andy.mengzhu.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.andy.greendao.Category;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.DaoMaster;
import com.andy.greendao.DaoSession;
import com.andy.greendao.Funds;
import com.andy.greendao.FundsDao;
import com.andy.mengzhu.presenter.CategoryPresenter;
import com.andy.mengzhu.presenter.FundsPresenter;
import com.andy.mengzhu.presenter.impl.CategoryPresenterImpl;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class AndyApplication extends Application{
    /**
     * 访问的数据库
     */
    private SQLiteDatabase db;
    /**
     * GreenDao 框架生成的主要类
     */
    private DaoMaster daoMaster;

    /**
     * 会话层，操作具体 Dao 层对象
     */
    private DaoSession daoSession;

    /**
     * 判断用户是否是安装APP后第一次使用
     */
    private boolean isFirstUse;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDatabase();
        isFirstUse();
    }

    /**
     * 用户第一次使用该APP时的操作
     */
    private void isFirstUse() {
        SharedPreferences preferences = getSharedPreferences("first",
                MODE_PRIVATE);
        isFirstUse = preferences.getBoolean("isFirstUse", true);
        if (isFirstUse) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstUse", false);
            editor.commit();
            initDatabase();
        }
    }


    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return this.daoSession;
    }

    /**
     * 初始化数据库数据
     */
    private void initDatabase() {
        //类目项
        //类目的类型： 0为支出、1为收入、2为收借款、3为转账
        String[] categoryName = {"交通", "主餐", "水果", "零食", "通讯", "生活用品", "借款", "工资", "收款", "转账"};
        int[] categoryType = {0, 0, 0, 0, 0, 0, 2, 1, 2, 3};
        CategoryDao mCategoryDao = daoSession.getCategoryDao();
        for (int i = 0; i < categoryName.length; i++) {
            Category category = new Category();
            category.setCategory_name(categoryName[i]);
            category.setType(categoryType[i]);
            mCategoryDao.insert(category);
        }

        //资金项
        FundsDao mFundsDao = daoSession.getFundsDao();
        String[] fundsName = {"银行卡", "零钱"};
        FundsPresenter mFundsPresenter = null;
        for (int i = 0; i < fundsName.length; i++) {
            Funds mFunds = new Funds();
            mFunds.setFunds_name(fundsName[i]);
            mFundsDao.insert(mFunds);
        }
    }
}
