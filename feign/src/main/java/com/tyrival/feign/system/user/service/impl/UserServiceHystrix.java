package com.tyrival.feign.system.user.service.impl;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.feign.system.user.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@Component
public class UserServiceHystrix extends FeignServiceHystrix<User> implements UserService {

    @Override
    public Result login(User user) {
        return new Result(ExceptionEnum.HYSTRIX);
    }

    @Override
    public Result<User> changePassword(String originalPwd, String newPwd, String repeatPwd)  {
        return new Result(ExceptionEnum.HYSTRIX);
    }
}
