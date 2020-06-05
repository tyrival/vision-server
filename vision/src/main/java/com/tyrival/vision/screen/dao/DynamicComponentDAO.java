package com.tyrival.vision.screen.dao;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.vision.screen.DynamicComponent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DynamicComponentDAO extends BaseDAO<DynamicComponent> {
}
