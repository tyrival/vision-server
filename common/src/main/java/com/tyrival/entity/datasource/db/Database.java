package com.tyrival.entity.datasource.db;

import com.google.gson.Gson;
import com.tyrival.entity.datasource.DataSource;

public class Database extends DataSource {

    /**
     * 域名
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 数据库
     */
    private String db;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getJdbcUrl() {
        return this.getType().getJdbcUrl(this);
    }

    public String getDriverClass() {
        return this.getType().getDriver();
    }

}
