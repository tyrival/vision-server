package com.tyrival.system.role.service.impl;

import com.tyrival.entity.system.role.Role;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.system.role.dao.RoleDAO;
import com.tyrival.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public BaseDAO<Role> getDAO() {
        return roleDAO;
    }
}
