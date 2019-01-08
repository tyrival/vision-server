package com.tyrival.api.base.dao;

import com.tyrival.entity.base.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public interface BaseDAO<T> {

    /**
     * 创建
     */
    int insert(T t);

    /**
     * 修改
     */
    int update(T t);

    /**
     * 删除
     */
    int delete(String id);

    /**
     * 根据ID查询
     */
    T get(String id);

    /**
     * 查询
     * @param queryParam 查询参数
     * @return 查询结果
     */
    List<T> list(@Param("queryParam") QueryParam queryParam);

}
