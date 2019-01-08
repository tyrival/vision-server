package com.tyrival.entity.base;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/27
 * @Version: V1.0
 */
public class Parameter {
    /**
     * 页码
     */
    private Integer pageIndex;
    /**
     * 每页行数
     */
    private Integer pageSize;
    /**
     * 排序
     */
    private String order;
    /**
     * 过滤条件
     */
    private String filter;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
