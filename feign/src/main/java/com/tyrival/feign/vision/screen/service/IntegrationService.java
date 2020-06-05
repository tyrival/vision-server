package com.tyrival.feign.vision.screen.service;

import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Integration;
import com.tyrival.feign.vision.screen.service.impl.IntegrationServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = ServiceConfig.VISION, path= ControllerConfig.INTEGRATION, fallback = IntegrationServiceHystrix.class)
public interface IntegrationService extends FeignService<Integration> {

    @RequestMapping(value = "/list_by_user")
    Result listByUser();

    @RequestMapping(value = "/preview")
    Result preview(@RequestBody Integration integration);

    @RequestMapping(value = "/publish")
    Result publish(@RequestBody Integration integration);
}
