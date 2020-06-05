package com.tyrival.feign.vision.template.service;

import com.tyrival.api.feign.service.FeignService;
import com.tyrival.config.ControllerConfig;
import com.tyrival.config.ServiceConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Template;
import com.tyrival.feign.vision.template.service.impl.TemplateServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = ServiceConfig.VISION, path = ControllerConfig.TEMPLATE, fallback = TemplateServiceHystrix.class)
public interface TemplateService extends FeignService<Template> {

    @RequestMapping(value = "/preview")
    Result<Template> preview(@RequestBody Template template);

    @RequestMapping(value = "/publish")
    Result<Template> publish(@RequestBody Template template);

    @RequestMapping("/list_by_user")
    Result listByUser();
}
