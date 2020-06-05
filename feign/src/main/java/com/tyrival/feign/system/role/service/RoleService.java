package com.tyrival.feign.system.role.service;

import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.system.role.Role;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.role.service.impl.RoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceConfig.SYSTEM, path= ControllerConfig.ROLE, fallback = RoleServiceHystrix.class)
public interface RoleService extends FeignService<Role> {

}
