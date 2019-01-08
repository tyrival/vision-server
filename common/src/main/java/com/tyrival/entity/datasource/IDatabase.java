package com.tyrival.entity.datasource;

import com.tyrival.entity.datasource.db.Database;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/14
 * @Version: V1.0
 */
public interface IDatabase {

    /**
     * 获取数据库连接字符串
     * @return
     */
    String getJdbcUrl(Database database);
}
