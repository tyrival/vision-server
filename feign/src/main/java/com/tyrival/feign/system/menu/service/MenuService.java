package com.tyrival.feign.system.menu.service;

import com.tyrival.entity.system.menu.Menu;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.menu.service.impl.MenuServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "server-system", path="/menu", fallback = MenuServiceHystrix.class)
public interface MenuService extends FeignService<Menu> {

}
