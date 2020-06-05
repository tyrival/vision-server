package com.tyrival.feign.vision.screen.service;

import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.vision.screen.DynamicComponent;
import com.tyrival.feign.vision.screen.service.impl.DynamicComponentServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceConfig.VISION, path= ControllerConfig.DYNAMIC_COMPONENT, fallback = DynamicComponentServiceHystrix.class)
public interface DynamicComponentService extends FeignService<DynamicComponent> {

}
