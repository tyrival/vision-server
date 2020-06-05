package com.tyrival.system.menu.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.system.menu.service.MenuService;
import com.tyrival.api.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.MENU)
public class MenuController extends BaseController<Menu> {

    @Autowired
    private MenuService menuService;

    @Override
    public BaseService getService() {
        return menuService;
    }
}
