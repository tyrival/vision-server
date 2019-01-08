package com.tyrival.feign.system.role.service.impl;

import com.tyrival.entity.system.role.Role;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.feign.system.role.service.RoleService;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceHystrix extends FeignServiceHystrix<Role> implements RoleService {
}
