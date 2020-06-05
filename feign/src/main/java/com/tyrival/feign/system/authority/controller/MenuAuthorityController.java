package com.tyrival.feign.system.authority.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.MenuAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.MENU_AUTHORITY)
public class MenuAuthorityController extends FeignController<MenuAuthority> {

    @Autowired
    private MenuAuthorityService menuAuthorityService;

    @Override
    public FeignService getService() {
        return menuAuthorityService;
    }

}
