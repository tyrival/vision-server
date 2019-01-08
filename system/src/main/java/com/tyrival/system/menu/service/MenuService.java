package com.tyrival.system.menu.service;

import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.base.service.BaseService;

public interface MenuService extends BaseService<Menu> {

    Menu getTreeById(String id);
}
