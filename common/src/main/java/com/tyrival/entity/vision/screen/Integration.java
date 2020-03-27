package com.tyrival.entity.vision.screen;

import com.google.gson.Gson;
import com.tyrival.entity.base.Base;
import com.tyrival.enums.vision.screen.ScreenStateEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Integration extends Base {

    /**
     * 名称
     */
    private String name;

    /**
     * 说明
     */
    private String description;

    /**
     * 封面
     */
    private String cover;

    /**
     * 布局类型
     */
    private String layout;

    /**
     * 导航配置
     */
    private String navigation;

    /**
     * 基础设置
     */
    private String base;

    /**
     * 发布状态 {@link ScreenStateEnum }
     */
    private ScreenStateEnum state;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
