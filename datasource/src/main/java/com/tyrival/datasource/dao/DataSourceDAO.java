package com.tyrival.datasource.dao;

import com.tyrival.datasource.annotation.DynamicDatabase;
import com.tyrival.entity.datasource.DataSource;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.db.DbQuery;
import com.tyrival.entity.datasource.file.FileSource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DataSourceDAO {

    /**
     * 新增数据源
     * @return
     */
    int insertDb(Database database);
    int insertApi(ApiSource apiSource);
    int insertFile(FileSource fileSource);

    /**
     * 修改数据源
     * @return
     */
    int updateDb(Database database);
    int updateApi(ApiSource apiSource);
    int updateFile(FileSource fileSource);

    /**
     * 删除数据源
     * @param query
     * @return
     */
    int deleteDb(DataSourceQuery query);
    int deleteApi(DataSourceQuery query);
    int deleteFile(DataSourceQuery query);

    /**
     * 获得数据源
     * @param query
     * @return
     */
    Database getDb(DataSourceQuery query);
    ApiSource getApi(DataSourceQuery query);
    FileSource getFile(DataSourceQuery query);

    /**
     * 获取用户的数据源列表
     * @param query
     * @return
     */
    List<DataSource> listByUser(DataSourceQuery query);
    List<Database> listDbByUser(DataSourceQuery query);
    List<ApiSource> listApiByUser(DataSourceQuery query);
    List<FileSource> listFileByUser(DataSourceQuery query);

    /**
     * 删除数据源
     * @param dataSourceQuery
     * @return
     */
    int delete(DataSourceQuery dataSourceQuery);

    /**
     * 查询数据库数据源
     * @param dbQuery
     * @return
     */
    @DynamicDatabase
    List<Map<String, Object>> queryDb(DbQuery dbQuery);


    /**
     * 查询Db
     */
    @DynamicDatabase
    List<String> listDb(Database database);
}
