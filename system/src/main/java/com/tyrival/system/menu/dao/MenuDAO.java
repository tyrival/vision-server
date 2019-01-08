package com.tyrival.system.menu.dao;

import com.tyrival.entity.system.menu.*;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MenuDAO extends BaseDAO<Menu> {

}
