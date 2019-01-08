package com.tyrival.system.authority.controller;

import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.system.authority.service.UserRoleService;
import com.tyrival.api.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_role")
public class UserRoleController extends BaseController<UserRole> {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public BaseService getService() {
        return userRoleService;
    }
}
