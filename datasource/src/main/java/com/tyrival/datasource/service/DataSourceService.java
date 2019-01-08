package com.tyrival.datasource.service;

import com.tyrival.entity.datasource.DataSource;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.db.components.Schema;
import com.tyrival.entity.datasource.file.FileSource;

import java.util.List;
import java.util.Map;

public interface DataSourceService<T extends DataSource> {

    /**
     * 新增数据源
     *
     * @return
     */
    int create(T t);
    int createDb(Database database);
    int createApi(ApiSource apiSource);
    int createFile(FileSource fileSource);

    /**
     * 修改数据源
     * @return
     */
    int update(T t);
    int updateDb(Database database);
    int updateApi(ApiSource apiSource);
    int updateFile(FileSource fileSource);

    /**
     * 删除数据源信息
     *
     * @param dataSourceQuery
     * @return
     */
    int delete(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据源信息
     *
     * @param dataSourceQuery
     * @return
     */
    Object get(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据源信息
     *
     * @param dataSourceQuery
     * @return
     */
    List listByUser(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据源信息
     *
     * @param dataSourceQuery
     * @return
     */
    List listDbByUser(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据源信息
     *
     * @param dataSourceQuery
     * @return
     */
    List listFileByUser(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据
     */
    List<Map<String, Object>> query(DataSourceQuery dbQuery);

    /**
     * 查询Schema
     */
    List<Schema> listSchema(Database database);
}
