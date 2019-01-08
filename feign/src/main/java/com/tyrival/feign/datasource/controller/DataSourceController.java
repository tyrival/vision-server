package com.tyrival.feign.datasource.controller;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.datasource.DataSource;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.file.FileSource;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.feign.datasource.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping(value = "/save_database")
    public Result<DataSource> saveDatabase(@RequestBody Database database) {
        return this.dataSourceService.saveDatabase(database);
    }

    @RequestMapping(value = "/save_api")
    public Result<DataSource> saveApi(@RequestBody ApiSource apiSource) {
        return this.dataSourceService.saveApi(apiSource);
    }

    @RequestMapping(value = "/save_file")
    public Result<DataSource> saveFile(@RequestBody FileSource fileSource) {
        return this.dataSourceService.saveFile(fileSource);
    }

    @RequestMapping(value = "/delete")
    public Result<DataSource> delete(@RequestBody DataSourceQuery query) {
        return this.dataSourceService.delete(query);
    }

    @RequestMapping(value = "/get")
    public Result<DataSource> get(@RequestBody DataSourceQuery query) {
        return this.dataSourceService.get(query);
    }

    @RequestMapping(value = "/list_by_user")
    public Result<DataSource> listByUser() {
        return this.dataSourceService.listByUser();
    }

    @RequestMapping(value = "/list_db_by_user")
    public Result<DataSource> listDbByUser() {
        return this.dataSourceService.listDbByUser();
    }

    @RequestMapping(value = "/list_file_by_user")
    public Result<DataSource> listFileByUser() {
        return this.dataSourceService.listFileByUser();
    }

    @RequestMapping(value = "/query")
    public Result<DataSource> query(@RequestBody DataSourceQuery query) {
        return this.dataSourceService.query(query);
    }

    @RequestMapping(value = "/list_schema")
    public Result<DataSource> listSchema(@RequestBody Database database) {
        return this.dataSourceService.listSchema(database);
    }
    @RequestMapping(value = "/api_proxy")
    public Result<DataSource> apiProxy(@RequestBody HttpRequest request) {
        return this.dataSourceService.apiProxy(request);
    }
}
