package com.tyrival.entity.datasource.db;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/13
 * @Version: V1.0
 */
public class DbQuery extends Database {

    /**
     * 查询sql
     */
    private String sql;

    public DbQuery(Database database, String sql) {
        this.setId(database.getId());
        this.setHost(database.getHost());
        this.setPort(database.getPort());
        this.setSchema(database.getSchema());
        this.setUserName(database.getUserName());
        this.setPassword(database.getPassword());
        this.setType(database.getType());
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
