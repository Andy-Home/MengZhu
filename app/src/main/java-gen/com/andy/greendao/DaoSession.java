package com.andy.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.andy.greendao.Record;
import com.andy.greendao.Category;
import com.andy.greendao.Funds;
import com.andy.greendao.Person;

import com.andy.greendao.RecordDao;
import com.andy.greendao.CategoryDao;
import com.andy.greendao.FundsDao;
import com.andy.greendao.PersonDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig recordDaoConfig;
    private final DaoConfig categoryDaoConfig;
    private final DaoConfig fundsDaoConfig;
    private final DaoConfig personDaoConfig;

    private final RecordDao recordDao;
    private final CategoryDao categoryDao;
    private final FundsDao fundsDao;
    private final PersonDao personDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        recordDaoConfig = daoConfigMap.get(RecordDao.class).clone();
        recordDaoConfig.initIdentityScope(type);

        categoryDaoConfig = daoConfigMap.get(CategoryDao.class).clone();
        categoryDaoConfig.initIdentityScope(type);

        fundsDaoConfig = daoConfigMap.get(FundsDao.class).clone();
        fundsDaoConfig.initIdentityScope(type);

        personDaoConfig = daoConfigMap.get(PersonDao.class).clone();
        personDaoConfig.initIdentityScope(type);

        recordDao = new RecordDao(recordDaoConfig, this);
        categoryDao = new CategoryDao(categoryDaoConfig, this);
        fundsDao = new FundsDao(fundsDaoConfig, this);
        personDao = new PersonDao(personDaoConfig, this);

        registerDao(Record.class, recordDao);
        registerDao(Category.class, categoryDao);
        registerDao(Funds.class, fundsDao);
        registerDao(Person.class, personDao);
    }

    public void clear() {
        recordDaoConfig.getIdentityScope().clear();
        categoryDaoConfig.getIdentityScope().clear();
        fundsDaoConfig.getIdentityScope().clear();
        personDaoConfig.getIdentityScope().clear();
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public FundsDao getFundsDao() {
        return fundsDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

}
