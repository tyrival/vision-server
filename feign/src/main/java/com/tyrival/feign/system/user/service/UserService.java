package com.tyrival.feign.system.user.service;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.user.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@FeignClient(value = "server-system", path = "/user", fallback = UserServiceHystrix.class)
public interface UserService extends FeignService<User> {

    @RequestMapping(value = "/login")
    Result login(@RequestBody User user);

    @RequestMapping(value = "/change_password")
    Result<User> changePassword(@RequestParam(value = "originalPwd") String originalPwd,
                                @RequestParam(value = "newPwd") String newPwd,
                                @RequestParam(value = "repeatPwd") String repeatPwd);
}
