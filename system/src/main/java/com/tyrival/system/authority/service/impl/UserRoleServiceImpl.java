package com.tyrival.system.authority.service.impl;

import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.system.authority.dao.UserRoleDAO;
import com.tyrival.system.authority.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public BaseDAO<UserRole> getDAO() {
        return userRoleDAO;
    }
}
