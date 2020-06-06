package com.tyrival.entity.vision.screen;

import com.google.gson.Gson;
import com.tyrival.entity.base.Base;

public class DynamicComponent extends Base {

    /**
     * 名称
     */
    private String text;

    /**
     * 分类
     */
    private String category;

    /**
     * 动态组件类型
     */
    private String name;

    /**
     * 配置
     */
    private String config;

    /**
     * 图片
     */
    private String image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
