package com.tyrival.system.authority.dao;

import com.tyrival.entity.system.authority.*;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.authority.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserRoleDAO extends BaseDAO<UserRole> {

}
