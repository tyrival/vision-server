package com.tyrival.datasource.config;

import com.tyrival.entity.datasource.db.Database;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
public class DataSourceHolder {

    /**
     * 线程本地环境
     */
    private static final ThreadLocal<Database> contextHolders = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param database
     */
    public static void setDataSource(Database database) {
        contextHolders.set(database);
    }

    /**
     * 获取数据源
     * @return
     */
    public static Database getDataSource() {
        return contextHolders.get();
    }

    /**
     * 清除数据源
     */
    public static void clear() {
        contextHolders.remove();
    }
}