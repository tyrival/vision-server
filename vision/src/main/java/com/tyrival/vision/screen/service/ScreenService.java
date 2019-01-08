package com.tyrival.vision.screen.service;

import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.base.service.BaseService;

public interface ScreenService extends BaseService<Screen> {

    /**
     * 预览：生成id，用redis缓存15秒，前端通过id从redis读取配置信息
     */
    Screen preview(Screen screen);
}
