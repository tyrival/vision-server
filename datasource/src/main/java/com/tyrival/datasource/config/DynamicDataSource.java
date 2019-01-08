package com.tyrival.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tyrival.entity.datasource.db.Database;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/13
 * @Version: V1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 本地保存所有DataSource对象的缓存
     */
    private Map<Object, DataSource> targetDataSources = new HashMap<>();

    /**
     * 默认数据连接
     */
    private DataSource defaultDataSource;

    public DynamicDataSource(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    @Override
    protected Database determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        Database database = determineCurrentLookupKey();
        if (null == database || StringUtils.isEmpty(database.getId())) {
            return this.defaultDataSource;
        }
        DataSource dataSource = targetDataSources.get(database.getId());
        if (null == dataSource || !equalSource(database, (DruidDataSource) dataSource)) {
            dataSource = buildDataSource(database);
            targetDataSources.put(database.getId(), dataSource);
        }
        return dataSource;
    }

    private boolean equalSource(Database database, DruidDataSource dataSource) {
        if (database.getJdbcUrl().equals(dataSource.getRawJdbcUrl())
                && database.getDriverClass().equals(dataSource.getDriverClassName())
                && database.getUserName().equals(dataSource.getUsername())
                && database.getPassword().equals(dataSource.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 手动创建一个数据源
     *
     * @param database 数据库连接信息
     * @return org.apache.commons.dbcp.BasicDataSource
     **/
    private DataSource buildDataSource(Database database) {
        try {
            Properties props = new Properties();
            props.put("driverClassName", database.getType().getDriver());
            props.put("url", database.getType().getJdbcUrl(database));
            props.put("username", database.getUserName());
            props.put("password", database.getPassword());
            DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
            dataSource.setBreakAfterAcquireFailure(true);
            dataSource.setConnectionErrorRetryAttempts(0);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

    public void removeDataSource(String dataSourceId) {
        targetDataSources.remove(dataSourceId);
    }

    public void close() {
        DataSourceHolder.clear();
    }
}