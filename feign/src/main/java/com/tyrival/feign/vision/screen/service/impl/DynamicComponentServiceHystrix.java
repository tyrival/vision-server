package com.tyrival.feign.vision.screen.service.impl;

import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.entity.vision.screen.DynamicComponent;
import com.tyrival.feign.vision.screen.service.DynamicComponentService;
import org.springframework.stereotype.Component;

@Component
public class DynamicComponentServiceHystrix extends FeignServiceHystrix<DynamicComponent> implements DynamicComponentService {
}
