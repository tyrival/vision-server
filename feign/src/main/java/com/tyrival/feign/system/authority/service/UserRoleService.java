package com.tyrival.feign.system.authority.service;

import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.impl.UserRoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceConfig.SYSTEM, path= ControllerConfig.USER_ROLE, fallback = UserRoleServiceHystrix.class)
public interface UserRoleService extends FeignService<UserRole> {

}
