package com.tyrival.system.role.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.system.role.Role;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.system.role.service.RoleService;
import com.tyrival.api.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.ROLE)
public class RoleController extends BaseController<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public BaseService getService() {
        return roleService;
    }
}
