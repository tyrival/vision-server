package com.tyrival.vision.screen.controller;

import com.tyrival.api.base.controller.BaseController;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.DynamicComponent;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.vision.screen.service.DynamicComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ControllerConfig.DYNAMIC_COMPONENT)
public class DynamicComponentController extends BaseController<DynamicComponent> {

    @Autowired
    private DynamicComponentService dynamicComponentService;

    @Override
    public BaseService getService() {
        return dynamicComponentService;
    }
}
