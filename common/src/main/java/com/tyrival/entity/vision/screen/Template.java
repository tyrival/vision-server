package com.tyrival.entity.vision.screen;

import com.google.gson.Gson;
import com.tyrival.entity.base.Base;
import com.tyrival.enums.vision.screen.ScreenStateEnum;
import com.tyrival.enums.vision.screen.TemplateStateEnum;

public class Template extends Base {

    /**
     * 名称
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 基础属性
     */
    private String base;

    /**
     * 组件顺序
     */
    private String sequence;

    /**
     * 组件
     */
    private String components;

    /**
     * 发布状态 {@link ScreenStateEnum }
     */
    private ScreenStateEnum state;

    /**
     * 模板类型 {@link TemplateStateEnum }
     */
    private TemplateStateEnum type;

    /**
     *模板风格类型
     */
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public ScreenStateEnum getState() {
        return state;
    }

    public void setState(ScreenStateEnum state) {
        this.state = state;
    }

    public TemplateStateEnum getType() {
        return type;
    }

    public void setType(TemplateStateEnum type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
