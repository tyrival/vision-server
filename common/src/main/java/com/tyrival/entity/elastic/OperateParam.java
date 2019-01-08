package com.tyrival.entity.elastic;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.tyrival.enums.elastic.EsOperateEnum;

import java.util.Map;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/31
 * @Version: V1.0
 */
public class OperateParam<T> {

    /**
     * id
     */
    private String id;

    /**
     * 对象json字符串
     */
    private String json;

    /**
     * es index
     */
    private String index;

    /**
     * es type
     */
    private String type;

    /**
     * 操作类型
     */
    private EsOperateEnum operate;

    /**
     * 是否异步，默认否
     */
    private boolean async;

    /**
     * json对象
     */
    private Map<String, Object> map;

    public OperateParam() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
        this.map = JSONObject.parseObject(json);
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public EsOperateEnum getOperate() {
        return operate;
    }

    public void setOperate(EsOperateEnum operate) {
        this.operate = operate;
    }

    public boolean getAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
