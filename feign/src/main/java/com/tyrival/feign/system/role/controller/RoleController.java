package com.tyrival.feign.system.role.controller;

import com.tyrival.entity.system.role.Role;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends FeignController<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public FeignService getService() {
        return roleService;
    }

}
