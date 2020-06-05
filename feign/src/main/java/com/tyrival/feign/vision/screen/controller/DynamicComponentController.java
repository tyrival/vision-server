package com.tyrival.feign.vision.screen.controller;

import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.vision.screen.DynamicComponent;
import com.tyrival.feign.vision.screen.service.DynamicComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.DYNAMIC_COMPONENT)
public class DynamicComponentController extends FeignController<DynamicComponent> {

    @Autowired
    private DynamicComponentService dynamicComponentService;

    @Override
    public FeignService getService() {
        return dynamicComponentService;
    }
}
