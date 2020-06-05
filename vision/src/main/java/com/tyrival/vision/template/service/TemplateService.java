package com.tyrival.vision.template.service;


import com.tyrival.api.base.service.BaseService;
import com.tyrival.entity.vision.screen.Template;

import java.util.List;

public interface TemplateService extends BaseService<Template> {

    /**
     * 预览：生成id，用redis缓存15秒，前端通过id从redis读取配置信息
     */
    Template preview(Template template);

    List<Template> listByUser(String id);
}
