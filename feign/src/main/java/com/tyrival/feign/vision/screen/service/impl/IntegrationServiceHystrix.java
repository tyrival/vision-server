package com.tyrival.feign.vision.screen.service.impl;

import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Integration;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.feign.vision.screen.service.IntegrationService;
import org.springframework.stereotype.Component;

@Component
public class IntegrationServiceHystrix extends FeignServiceHystrix<Integration> implements IntegrationService {

    @Override
    public Result listByUser() {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result<Integration> preview(Integration integration)  {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result<Integration> publish(Integration integration) {
        return new Result(ExceptionEnum.HYSTRIX);
    }
}
