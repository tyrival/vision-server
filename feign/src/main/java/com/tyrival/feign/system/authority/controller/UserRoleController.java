package com.tyrival.feign.system.authority.controller;

import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_role")
public class UserRoleController extends FeignController<UserRole> {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public FeignService getService() {
        return userRoleService;
    }

}
