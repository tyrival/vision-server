package com.tyrival.vision.screen.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.enums.vision.screen.ScreenStateEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.vision.screen.service.ScreenService;
import com.tyrival.api.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ControllerConfig.SCREEN)
public class ScreenController extends BaseController<Screen> {

    @Autowired
    private ScreenService screenService;

    @Override
    public BaseService getService() {
        return screenService;
    }

    @Override
    @RequestMapping("/get")
    public Result get(@RequestParam("id") String id) {
        try {
            return new Result(this.screenService.get(id));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.QUERY_FAIL);
        }
    }

    @RequestMapping("/preview")
    public Result preview(@RequestBody Screen screen) {
        try {
            screen.setId(UUID.randomUUID().toString());
            return new Result(this.screenService.preview(screen));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PREVIEW_FAIL);
        }
    }

    @RequestMapping("/publish")
    public Result publish(@RequestBody Screen screen) {
        try {
            return this.save(screen);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PUBLISH_FAIL);
        }
    }
}
