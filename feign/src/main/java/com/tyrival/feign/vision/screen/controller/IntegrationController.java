package com.tyrival.feign.vision.screen.controller;

import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Integration;
import com.tyrival.feign.vision.screen.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.INTEGRATION)
public class IntegrationController extends FeignController<Integration> {

    @Autowired
    private IntegrationService integrationService;

    @Override
    public FeignService getService() {
        return integrationService;
    }

    @RequestMapping(value = "/list_by_user")
    public Result listByUser() {
        return this.integrationService.listByUser();
    }

    @RequestMapping(value = "/preview")
    public Result<Integration> preview(@RequestBody Integration integration) {
        return this.integrationService.preview(integration);
    }

    @RequestMapping(value = "/publish")
    public Result<Integration> publish(@RequestBody Integration integration) {
        return this.integrationService.publish(integration);
    }
}
