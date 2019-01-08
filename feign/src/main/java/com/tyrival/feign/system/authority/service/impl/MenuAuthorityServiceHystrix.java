package com.tyrival.feign.system.authority.service.impl;

import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.feign.system.authority.service.MenuAuthorityService;
import org.springframework.stereotype.Component;

@Component
public class MenuAuthorityServiceHystrix extends FeignServiceHystrix<MenuAuthority> implements MenuAuthorityService {
}
