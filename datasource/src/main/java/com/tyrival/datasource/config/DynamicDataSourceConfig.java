package com.tyrival.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tyrival.enums.datasource.DatabaseEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
@Configuration
public class DynamicDataSourceConfig {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据    库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource defaultDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", driverClassName);
        props.put("url", url);
        props.put("username", username);
        props.put("password", password);
        DruidDataSource dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        dataSource.setBreakAfterAcquireFailure(true);
        dataSource.setConnectionErrorRetryAttempts(0);
        return dataSource;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("defaultDataSource") DataSource defaultDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseEnum.DEFAULT, defaultDataSource);
        DynamicDataSource dataSource = new DynamicDataSource(defaultDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(defaultDataSource);
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("defaultDataSource") DataSource defaultDataSource) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(this.dataSource(defaultDataSource));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**/*.xml"));
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
