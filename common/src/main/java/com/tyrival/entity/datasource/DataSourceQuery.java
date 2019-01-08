package com.tyrival.entity.datasource;

import com.tyrival.entity.base.Parameter;
import com.tyrival.enums.datasource.DataSourceTypeEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/13
 * @Version: V1.0
 */
public class DataSourceQuery extends Parameter {

    /**
     * 数据源id
     */
    private String dsId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 类型
     */
    private DataSourceTypeEnum type;

    /**
     * 查询语句
     */
    private String sql;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public DataSourceTypeEnum getType() {
        return type;
    }

    public void setType(DataSourceTypeEnum type) {
        this.type = type;
    }
}
