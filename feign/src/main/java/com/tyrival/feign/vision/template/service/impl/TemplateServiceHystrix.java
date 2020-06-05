package com.tyrival.feign.vision.template.service.impl;

import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Template;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.feign.vision.template.service.TemplateService;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceHystrix extends FeignServiceHystrix<Template> implements TemplateService {

    @Override
    public Result<Template> preview(Template template)  {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result<Template> publish(Template template) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result listByUser() {
        return new Result(ExceptionEnum.HYSTRIX);
    }
}
