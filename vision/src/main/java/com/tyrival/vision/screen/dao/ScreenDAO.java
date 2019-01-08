package com.tyrival.vision.screen.dao;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.vision.screen.Screen;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ScreenDAO extends BaseDAO<Screen> {
}
