package com.tyrival.feign.vision.screen.service.impl;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.vision.screen.Screen;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.feign.vision.screen.service.ScreenService;
import org.springframework.stereotype.Component;

@Component
public class ScreenServiceHystrix extends FeignServiceHystrix<Screen> implements ScreenService {

    @Override
    public Result<Screen> preview(Screen screen)  {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }

    @Override
    public Result<Screen> publish(Screen screen) {
        return new Result(new CommonException(ExceptionEnum.HYSTRIX));
    }
}
