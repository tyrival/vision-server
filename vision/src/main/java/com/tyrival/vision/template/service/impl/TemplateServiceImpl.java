package com.tyrival.vision.template.service.impl;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.entity.vision.screen.Template;
import com.tyrival.redis.annotation.CacheExpire;
import com.tyrival.vision.template.dao.TemplateDAO;
import com.tyrival.vision.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl extends BaseServiceImpl<Template> implements TemplateService {

    @Autowired
    private TemplateDAO templateDAO;

    @Override
    public BaseDAO<Template> getDAO() {
        return templateDAO;
    }

    @Override
    @Cacheable(value = "template", key = "#id")
    public Template get(String id) {
        return this.getDAO().get(id);
    }

    @Override
    @Cacheable(value = "template", key = "#template.id")
    @CacheExpire(expire = 10)
    public Template preview(Template template) {
        return template;
    }

    @Override
    public List<Template> listByUser(String id) {
        return this.templateDAO.listByUser(id);
    }
}
