package com.tyrival.feign.system.authority.service;

import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.impl.MenuAuthorityServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceConfig.SYSTEM, path= ControllerConfig.MENU_AUTHORITY, fallback = MenuAuthorityServiceHystrix.class)
public interface MenuAuthorityService extends FeignService<MenuAuthority> {

}
