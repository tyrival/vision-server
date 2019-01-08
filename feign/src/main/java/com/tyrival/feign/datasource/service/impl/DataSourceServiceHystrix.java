package com.tyrival.feign.datasource.service.impl;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.file.FileSource;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.feign.datasource.service.DataSourceService;
import org.springframework.stereotype.Component;

@Component
public class DataSourceServiceHystrix implements DataSourceService {

    @Override
    public Result saveDatabase(Database database) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result saveApi(ApiSource apiSource) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result saveFile(FileSource fileSource) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result delete(DataSourceQuery query) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result get(DataSourceQuery query) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result listByUser() {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result listDbByUser() {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result listFileByUser() {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result query(DataSourceQuery query) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result listSchema(Database database) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result apiProxy(HttpRequest request) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }
}
