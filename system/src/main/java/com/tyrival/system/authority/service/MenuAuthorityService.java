package com.tyrival.system.authority.service;

import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.entity.system.authority.MenuAuthorityVo;
import com.tyrival.entity.system.menu.Menu;
import com.tyrival.entity.system.user.User;

import java.util.List;

public interface MenuAuthorityService extends BaseService<MenuAuthority> {

    List<MenuAuthorityVo> getTreeByUser(User user);

    Menu getTreeByIdAndUser(User user, String id);
}
