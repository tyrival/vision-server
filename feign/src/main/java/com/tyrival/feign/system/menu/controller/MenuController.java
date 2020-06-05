package com.tyrival.feign.system.menu.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.MENU)
public class MenuController extends FeignController<Menu> {

    @Autowired
    private MenuService menuService;

    @Override
    public FeignService getService() {
        return menuService;
    }

}
