package com.tyrival.vision.screen.controller;

import com.tyrival.api.base.controller.BaseController;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.entity.vision.screen.Integration;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.vision.screen.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ControllerConfig.INTEGRATION)
public class IntegrationController extends BaseController<Integration> {

    @Autowired
    private IntegrationService integrationService;

    @Override
    public BaseService getService() {
        return integrationService;
    }

    @RequestMapping("/list_by_user")
    public Result listByUser() {
        try {
            User user = this.getCurrentUser();
            if (user == null) {
                return new Result(ExceptionEnum.QUERY_FAIL);
            }
            String id = user.getId();
            return new Result(this.integrationService.listByUser(id));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.QUERY_FAIL);
        }
    }

    @Override
    @RequestMapping("/get")
    public Result get(@RequestParam("id") String id) {
        try {
            return new Result(this.integrationService.get(id));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.QUERY_FAIL);
        }
    }

    @RequestMapping("/preview")
    public Result preview(@RequestBody Integration integration) {
        try {
            integration.setId(UUID.randomUUID().toString());
            return new Result(this.integrationService.preview(integration));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PREVIEW_FAIL);
        }
    }

    @RequestMapping("/publish")
    public Result publish(@RequestBody Integration integration) {
        try {
            return this.save(integration);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PUBLISH_FAIL);
        }
    }
}
