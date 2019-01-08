package com.tyrival.api.feign.controller;

import com.tyrival.entity.base.Parameter;
import com.tyrival.entity.base.Result;
import com.tyrival.api.feign.service.FeignService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/26
 * @Version: V1.0
 */
public abstract class FeignController<T> {

    public abstract FeignService getService();

    @RequestMapping(value = "/save")
    public Result<T> save(@RequestBody T t) {
        return this.getService().save(t);
    }

    @RequestMapping(value = "/create")
    public Result<T> create(@RequestBody T t) {
        return this.getService().create(t);
    }

    @RequestMapping(value = "/update")
    public Result<T> update(@RequestBody T t) {
        return this.getService().update(t);
    }

    @RequestMapping(value = "/delete")
    public Result<T> delete(@RequestParam("id") String id) {
        return this.getService().delete(id);
    }

    @RequestMapping(value = "/get")
    public Result<T> get(@RequestParam("id") String id) {
        return this.getService().get(id);
    }

    @RequestMapping(value = "/list")
    public Result<T> list(@RequestBody Parameter parameter) {
        return this.getService().list(parameter);
    }

    @RequestMapping(value = "/list_by_page")
    public Result<T> listByPage(@RequestBody Parameter parameter) {
        return this.getService().listByPage(parameter);
    }
}
