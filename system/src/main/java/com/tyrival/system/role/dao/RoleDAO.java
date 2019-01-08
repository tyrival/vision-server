package com.tyrival.system.role.dao;

import com.tyrival.entity.system.role.*;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleDAO extends BaseDAO<Role> {

}
