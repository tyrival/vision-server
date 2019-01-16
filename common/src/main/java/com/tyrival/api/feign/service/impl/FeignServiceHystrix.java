package com.tyrival.api.feign.service.impl;

import com.tyrival.entity.base.Parameter;
import com.tyrival.entity.base.Result;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.api.feign.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/26
 * @Version: V1.0
 */
@Component
public class FeignServiceHystrix<T> implements FeignService<T> {

    @Override
    public Result save(T t) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result create(T t) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result update(T t) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result delete(String id) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result get(String id) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result list(Parameter parameter) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result listByPage(Parameter parameter) {
        return new Result(ExceptionEnum.HYSTRIX);
    }
}
