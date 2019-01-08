package com.tyrival.elastic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tyrival.elastic.service.ElasticService;
import com.tyrival.entity.elastic.SearchParam;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/7/6
 * @Version: V1.0
 */
@Service
public class ElasticServiceImpl<T> implements ElasticService<T> {

    @Autowired
    private RestHighLevelClient restClient;

    @Override
    public IndexResponse create(T t, String id, String index, String type) {
        try {
            JSONObject obj = (JSONObject) JSONObject.toJSON(t);
            IndexRequest request = new IndexRequest(index, type, id)
                    .source(obj, XContentType.JSON);
            IndexResponse response = restClient.index(request, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_CREATE_FAIL);
        }
    }

    @Override
    public void createAsync(T t, String id, String index, String type, ActionListener listener) {
        JSONObject obj = (JSONObject) JSONObject.toJSON(t);
        IndexRequest request = new IndexRequest(index, type, id)
                .source(obj, XContentType.JSON);
        restClient.indexAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public UpdateResponse update(T t, String id, String index, String type) {
        try {
            JSONObject obj = (JSONObject) JSONObject.toJSON(t);
            UpdateRequest request = new UpdateRequest(index, type, id)
                    .doc(obj, XContentType.JSON);
            UpdateResponse response = restClient.update(request, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_UPDATE_FAIL);
        }
    }

    @Override
    public void updateAsync(T t, String index, String id, String type, ActionListener listener) {
        JSONObject obj = (JSONObject) JSONObject.toJSON(t);
        UpdateRequest request = new UpdateRequest(index, type, id)
                .doc(obj, XContentType.JSON);
        restClient.updateAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public DeleteResponse delete(String id, String index, String type) {
        try {
            DeleteRequest request = new DeleteRequest(index, type, id);
            DeleteResponse response = restClient.delete(request, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_DELETE_FAIL);
        }
    }

    @Override
    public void deleteAsync(String id, String index, String type, ActionListener listener) {
        DeleteRequest request = new DeleteRequest(index, type, id);
        restClient.deleteAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public GetResponse get(String id, String index, String type) {
        try {
            GetRequest request = new GetRequest(index, type, id);
            GetResponse response = restClient.get(request, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_DELETE_FAIL);
        }
    }

    @Override
    public void getAsync(String id, String index, String type, ActionListener listener) {
        GetRequest request = new GetRequest(index, type, id);
        restClient.getAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public boolean exist(String id, String index, String type) {
        try {
            GetRequest request = new GetRequest(index, type, id)
                    .fetchSourceContext(new FetchSourceContext(false))
                    .storedFields("_none_");
            boolean exists = restClient.exists(request, RequestOptions.DEFAULT);
            return exists;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_DELETE_FAIL);
        }
    }

    @Override
    public void existAsync(String id, String index, String type, ActionListener listener) {
        GetRequest request = new GetRequest(index, type, id)
                .fetchSourceContext(new FetchSourceContext(false))
                .storedFields("_none_");
        restClient.existsAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Override
    public SearchResponse search(SearchParam param) {
        try {
            SearchRequest request = this.createSearchRequest(param);
            SearchResponse response = restClient.search(request, RequestOptions.DEFAULT);
            return response;
        } catch (IOException e) {
            throw new CommonException(ExceptionEnum.ELASTIC_QUERY_FAIL);
        }
    }

    /**
     * 实例化查询请求
     */
    private SearchRequest createSearchRequest(SearchParam param) {

        SearchRequest request = new SearchRequest();

        // 设置index
        if (StringUtils.isNotEmpty(param.getIndex())) {
            request.indices(param.getIndex());
        }
        if (StringUtils.isNotEmpty(param.getType())) {
            request.types(param.getType());
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 设置分页
        if (param.getFrom() != null) {
            sourceBuilder.from(param.getFrom());
        }
        if (param.getSize() != null) {
            sourceBuilder.size(param.getSize());
        }

        // 设置高亮
        if (param.getHighlights() != null && param.getHighlights().length > 0) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            for (String fieldName : param.getHighlights()) {
                highlightBuilder.field(createHighlightContent(fieldName));
            }
            sourceBuilder.highlighter(highlightBuilder);
        }

        // 查询关键词
        if (param.getFields() != null && param.getFields().length > 0) {
            // 搜索传入字段
            MultiMatchQueryBuilder multiMatchQueryBuilder
                    = QueryBuilders.multiMatchQuery(param.getText(), param.getFields())
                    .fuzziness(Fuzziness.AUTO);
            sourceBuilder.query(multiMatchQueryBuilder);
        } else {
            // 搜索所有字段
            QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders
                    .queryStringQuery(param.getText());
            sourceBuilder.query(queryStringQueryBuilder);
        }
        request.source(sourceBuilder);
        return request;
    }

    /**
     * 封装高亮查询字段
     *
     * @param fieldName 字段名
     * @return 高亮字段体
     */
    private HighlightBuilder.Field createHighlightContent(String fieldName) {
        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field(fieldName);
        highlightContent.highlighterType("unified");
//        highlightContent.fragmentSize(FRAGMENT_SIZE);
//        highlightContent.numOfFragments(FRAGMENT_NUM);
        return highlightContent;
    }
}
