package com.tyrival.system.authority.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.system.authority.service.MenuAuthorityService;
import com.tyrival.api.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.MENU_AUTHORITY)
public class MenuAuthorityController extends BaseController<MenuAuthority> {

    @Autowired
    private MenuAuthorityService menuAuthorityService;

    @Override
    public BaseService getService() {
        return menuAuthorityService;
    }
}
