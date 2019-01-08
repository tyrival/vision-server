package com.tyrival.feign.system.authority.service;

import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.impl.MenuAuthorityServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "server-system", path="/menu_authority", fallback = MenuAuthorityServiceHystrix.class)
public interface MenuAuthorityService extends FeignService<MenuAuthority> {

}
