package com.tyrival.vision.screen.service;

import com.tyrival.api.base.service.BaseService;
import com.tyrival.entity.vision.screen.Integration;

import java.util.List;

public interface IntegrationService extends BaseService<Integration> {

    /**
     * 预览：生成id，用redis缓存15秒，前端通过id从redis读取配置信息
     */
    Integration preview(Integration integration);

    List<Integration> listByUser(String id);
}
