package com.tyrival.elastic.service;

import com.tyrival.entity.elastic.SearchParam;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/7/6
 * @Version: V1.0
 */
public interface ElasticService<T> {

    /**
     * 创建
     */
    IndexResponse create(T t, String id, String index, String type);

    /**
     * 创建-异步
     */
    void createAsync(T t, String id, String index, String type, ActionListener listener);

    /**
     * 更新
     */
    UpdateResponse update(T t, String id, String index, String type);

    /**
     * 更新-异步
     */
    void updateAsync(T t, String id, String index, String type, ActionListener listener);

    /**
     * 删除
     */
    DeleteResponse delete(String id, String index, String type);

    /**
     * 删除-异步
     */
    void deleteAsync(String id, String index, String type, ActionListener listener);

    /**
     * 查询单个
     */
    GetResponse get(String id, String index, String type);

    /**
     * 查询单个-异步
     */
    void getAsync(String id, String index, String type, ActionListener listener);

    /**
     * 判断是否存在
     */
    boolean exist(String id, String index, String type);

    /**
     * 判断是否存在-异步
     */
    void existAsync(String id, String index, String type, ActionListener listener);

    /**
     * 查询
     */
    SearchResponse search(SearchParam param);

}
