package com.tyrival.vision.template.dao;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.vision.screen.Template;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TemplateDAO extends BaseDAO<Template> {

    List<Template> listByUser(String id);
}
