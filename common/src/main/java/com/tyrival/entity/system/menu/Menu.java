package com.tyrival.entity.system.menu;

import com.tyrival.entity.base.TreeNode;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/30
 * @Version: V1.0
 */
public class Menu extends TreeNode {

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * url
     */
    private String url;

    /**
     * hash路由地址
     */
    private String hash;

    /**
     * 排序
     */
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
