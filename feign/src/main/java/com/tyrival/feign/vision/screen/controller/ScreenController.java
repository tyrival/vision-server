package com.tyrival.feign.vision.screen.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.vision.screen.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.SCREEN)
public class ScreenController extends FeignController<Screen> {

    @Autowired
    private ScreenService screenService;

    @Override
    public FeignService getService() {
        return screenService;
    }

    @RequestMapping(value = "/preview")
    public Result<Screen> preview(@RequestBody Screen screen) {
        return this.screenService.preview(screen);
    }

    @RequestMapping(value = "/publish")
    public Result<Screen> publish(@RequestBody Screen screen) {
        return this.screenService.publish(screen);
    }
}
