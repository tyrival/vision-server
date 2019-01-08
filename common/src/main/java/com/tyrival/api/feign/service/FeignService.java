package com.tyrival.api.feign.service;

import com.tyrival.entity.base.Parameter;
import com.tyrival.entity.base.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/26
 * @Version: V1.0
 */
public interface FeignService<T> {

    @RequestMapping(value = "/save")
    Result<T> save(@RequestBody T t);

    @RequestMapping(value = "/create")
    Result<T> create(@RequestBody T t);

    @RequestMapping(value = "/update")
    Result<T> update(@RequestBody T t);

    @RequestMapping(value = "/delete")
    Result<T> delete(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/get")
    Result<T> get(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/list")
    Result<List<T>> list(@RequestBody Parameter parameter);

    @RequestMapping(value = "/list_by_page")
    Result<List<T>> listByPage(@RequestBody Parameter parameter);
}
