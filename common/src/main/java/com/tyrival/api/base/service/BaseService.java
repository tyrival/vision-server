package com.tyrival.api.base.service;

import com.tyrival.entity.base.Parameter;
import com.tyrival.entity.base.Result;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public interface BaseService<T> {

    T create(T t);

    T update(T t);

    int delete(String id);

    T get(String id);

    List<T> list();

    List<T> list(Parameter parameter);

    Result listByPage(Parameter parameter);
}
