package com.tyrival.feign.system.authority.service.impl;

import com.tyrival.entity.system.authority.UserRole;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.feign.system.authority.service.UserRoleService;
import org.springframework.stereotype.Component;

@Component
public class UserRoleServiceHystrix extends FeignServiceHystrix<UserRole> implements UserRoleService {
}
