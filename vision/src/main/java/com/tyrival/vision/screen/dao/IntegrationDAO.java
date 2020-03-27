package com.tyrival.vision.screen.dao;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.vision.screen.Integration;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IntegrationDAO extends BaseDAO<Integration> {
    List<Integration> listByUser(String id);
}
