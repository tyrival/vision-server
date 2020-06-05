package com.tyrival.feign.vision.screen.service;

import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.vision.screen.service.impl.ScreenServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = ServiceConfig.VISION, path= ControllerConfig.SCREEN, fallback = ScreenServiceHystrix.class)
public interface ScreenService extends FeignService<Screen> {

    @RequestMapping(value = "/preview")
    Result<Screen> preview(@RequestBody Screen screen);

    @RequestMapping(value = "/publish")
    Result<Screen> publish(@RequestBody Screen screen);
}
