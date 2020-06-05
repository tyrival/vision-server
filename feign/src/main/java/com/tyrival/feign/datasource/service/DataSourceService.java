package com.tyrival.feign.datasource.service;

import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.file.FileSource;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.feign.datasource.service.impl.DataSourceServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = ServiceConfig.DATASOURCE, path = ControllerConfig.DATASOURCE, fallback = DataSourceServiceHystrix.class)
public interface DataSourceService {

    @RequestMapping(value = "/save_database")
    Result saveDatabase(@RequestBody Database database);

    @RequestMapping(value = "/save_api")
    Result saveApi(@RequestBody ApiSource apiSource);

    @RequestMapping(value = "/save_file")
    Result saveFile(@RequestBody FileSource fileSource);

    @RequestMapping(value = "/delete")
    Result delete(@RequestBody DataSourceQuery query);

    @RequestMapping(value = "/get")
    Result get(@RequestBody DataSourceQuery query);

    @RequestMapping(value = "/list_by_user")
    Result listByUser();

    @RequestMapping(value = "/list_db_by_user")
    Result listDbByUser();

    @RequestMapping(value = "/list_file_by_user")
    Result listFileByUser();

    @RequestMapping(value = "/query")
    Result query(@RequestBody DataSourceQuery query);

    @RequestMapping(value = "/list_db")
    Result listDb(@RequestBody Database database);

    @RequestMapping(value = "/api_proxy")
    Result apiProxy(@RequestBody HttpRequest request);
}
