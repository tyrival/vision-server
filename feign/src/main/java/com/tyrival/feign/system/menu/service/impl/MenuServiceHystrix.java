package com.tyrival.feign.system.menu.service.impl;

import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.feign.system.menu.service.MenuService;
import org.springframework.stereotype.Component;

@Component
public class MenuServiceHystrix extends FeignServiceHystrix<Menu> implements MenuService {
}
