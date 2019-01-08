package com.tyrival.entity.datasource;

import com.tyrival.entity.base.Base;
import com.tyrival.enums.datasource.DataSourceTypeEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
public class DataSource extends Base {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private DataSourceTypeEnum type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSourceTypeEnum getType() {
        return type;
    }

    public void setType(DataSourceTypeEnum type) {
        this.type = type;
    }
}
