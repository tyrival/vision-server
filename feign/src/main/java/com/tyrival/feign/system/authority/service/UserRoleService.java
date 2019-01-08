package com.tyrival.feign.system.authority.service;

import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.authority.service.impl.UserRoleServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "server-system", path="/user_role", fallback = UserRoleServiceHystrix.class)
public interface UserRoleService extends FeignService<UserRole> {

}
