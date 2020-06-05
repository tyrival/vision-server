package com.tyrival.vision.template.controller;

import com.tyrival.api.base.controller.BaseController;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.entity.vision.screen.Template;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.vision.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(ControllerConfig.TEMPLATE)
public class TemplateController extends BaseController<Template> {

    @Autowired
    private TemplateService templateService;

    @Override
    public BaseService getService() {
        return templateService;
    }

    @RequestMapping("/list_by_user")
    public Result listByUser() {
        try {
            User user = this.getCurrentUser();
            if (user == null) {
                return new Result(ExceptionEnum.QUERY_FAIL);
            }
            String id = user.getId();
            return new Result(this.templateService.listByUser(id));
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
            return new Result(this.templateService.get(id));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.QUERY_FAIL);
        }
    }

    @RequestMapping("/preview")
    public Result preview(@RequestBody Template template) {
        try {
            template.setId(UUID.randomUUID().toString());
            return new Result(this.templateService.preview(template));
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PREVIEW_FAIL);
        }
    }

    @RequestMapping("/publish")
    public Result publish(@RequestBody Template template) {
        try {
            return this.save(template);
        } catch (Exception e) {
            return new Result(ExceptionEnum.SCREEN_PUBLISH_FAIL);
        }
    }
}
