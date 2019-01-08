package com.tyrival.enums.datasource;

import com.tyrival.entity.datasource.IDatabase;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.enums.BaseEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/11
 * @Version: V1.0
 */
public enum DataSourceTypeEnum implements BaseEnum, IDatabase {

    API(0, "API接口"),
    POSTGRESQL(10, "PostgreSQL数据库", "org.postgresql.Driver") {
        @Override
        public String getJdbcUrl(Database database) {
            StringBuilder url = new StringBuilder("jdbc:postgresql://")
                    .append(database.getHost())
                    .append(":")
                    .append(database.getPort())
                    .append("/")
                    .append(database.getSchema());
            return url.toString();
        }
    },
    MYSQL(11, "MySQL数据库", "com.mysql.cj.jdbc.Driver") {
        @Override
        public String getJdbcUrl(Database database) {
            StringBuilder url = new StringBuilder("jdbc:mysql://")
                    .append(database.getHost())
                    .append(":")
                    .append(database.getPort())
                    .append("/")
                    .append(database.getSchema())
                    .append("?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
            return url.toString();
        }
    },
    ORACLE(12, "Oracle数据库", "") {
        @Override
        public String getJdbcUrl(Database database) {
            StringBuilder url = new StringBuilder("jdbc:oracle:thin:@")
                    .append(database.getHost())
                    .append(":")
                    .append(database.getPort())
                    .append("/")
                    .append(database.getSchema());
            return url.toString();
        }
    },
    EXCEL(20, "XLS/CSV文件");

    private int code;
    private String msg;
    private String driver;

    DataSourceTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    DataSourceTypeEnum(int code, String msg, String driver) {
        this.code = code;
        this.msg = msg;
        this.driver = driver;
    }

    public boolean isApi() {
        return this.code < 10;
    }

    public boolean isDatabase() {
        return this.code >= 10 && this.code < 20;
    }

    public boolean isFile() {
        return this.code >= 20 && this.code < 30;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public String getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return "DataSourceTypeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }

    @Override
    public String getJdbcUrl(Database database) {
        throw new CommonException(ExceptionEnum.NOT_DB_SOURCE);
    }
}
