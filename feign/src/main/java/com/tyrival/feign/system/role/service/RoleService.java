package com.tyrival.feign.system.role.service;

import com.tyrival.entity.system.role.Role;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.role.service.impl.RoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "server-system", path="/role", fallback = RoleServiceHystrix.class)
public interface RoleService extends FeignService<Role> {

}
