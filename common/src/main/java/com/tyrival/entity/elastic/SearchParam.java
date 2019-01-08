package com.tyrival.entity.elastic;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/30
 * @Version: V1.0
 */
public class SearchParam {

    private static final String SEPARATOR = ",";

    /**
     * 库
     */
    private String index;

    /**
     * 表
     */
    private String type;

    /**
     * 页码
     */
    private Integer from;

    /**
     * 单页行数
     */
    private Integer size;

    /**
     * 搜索文本
     */
    private String text;

    /**
     * 搜索字段
     */
    private String field;

    /**
     * 搜索字段
     */
    private String[] fields;

    /**
     * 高亮字段
     */
    private String highlight;

    /**
     * 高亮字段
     */
    private String[] highlights;

    /**
     * 排序规则
     */
    private String order;

    /**
     * 是否精准匹配短语
     */
    private boolean isPhrase;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setField(String field) {
        this.field = field;
        this.fields = field.split(SEPARATOR);
        if (StringUtils.isEmpty(this.highlight)) {
            this.highlight = this.field;
            this.highlights = this.fields;
        }
    }

    public String[] getFields() {
        return fields;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
        this.highlights = highlight.split(SEPARATOR);
    }

    public String[] getHighlights() {
        return highlights;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isPhrase() {
        return isPhrase;
    }

    public void setPhrase(boolean phrase) {
        isPhrase = phrase;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
