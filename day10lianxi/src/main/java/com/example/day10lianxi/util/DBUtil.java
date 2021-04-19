package com.example.day10lianxi.util;

import com.example.mygreedaotest.dao.DaoMaster;
import com.example.mygreedaotest.dao.DaoSession;
import com.example.mygreedaotest.dao.DataDbBeanDao;

import java.util.List;

public class DBUtil {
    private static volatile DBUtil dbUtil;
    private final DataDbBeanDao dataDbBeanDao;

    public static DBUtil getDbUtil() {
        if (dbUtil==null){
            synchronized (DBUtil.class){
                if (dbUtil==null){
                    dbUtil = new DBUtil();
                }
            }
        }
        return dbUtil;
    }

    private DBUtil() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyAppGreenApplication.getAppGreenApplication(), "girls.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        dataDbBeanDao = daoSession.getDataDbBeanDao();
    }
    public void insert(DataDbBean dataDbBean){
        dataDbBeanDao.insertOrReplace(dataDbBean);
    }
    public void delete(DataDbBean dataDbBean){
        dataDbBeanDao.delete(dataDbBean);
    }
    public List<DataDbBean> query(String title){
        List<DataDbBean> list = dataDbBeanDao.queryBuilder().where(DataDbBeanDao.Properties.Title.eq(title)).list();
        return list;
    }
    public List<DataDbBean> queryAll(){
        return dataDbBeanDao.queryBuilder().list();
    }
}
