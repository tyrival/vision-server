package com.tyrival.feign.vision.template.controller;

import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Template;
import com.tyrival.feign.vision.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConfig.TEMPLATE)
public class TemplateController extends FeignController<Template> {

    @Autowired
    private TemplateService templateService;

    @Override
    public FeignService getService() {
        return templateService;
    }

    @RequestMapping(value = "/preview")
    public Result<Template> preview(@RequestBody Template template) {
        return this.templateService.preview(template);
    }

    @RequestMapping(value = "/publish")
    public Result<Template> publish(@RequestBody Template template) {
        return this.templateService.publish(template);
    }

    @RequestMapping("/list_by_user")
    public Result listByUser() {
        return this.templateService.listByUser();
    }
}
