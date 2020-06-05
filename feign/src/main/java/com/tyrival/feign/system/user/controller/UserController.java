package com.tyrival.feign.system.user.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.api.feign.controller.FeignController;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.feign.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@RestController
@RequestMapping(ControllerConfig.USER)
public class UserController extends FeignController<User> {

    @Autowired
    private UserService userService;

    @Override
    public FeignService getService() {
        return userService;
    }

    @RequestMapping(value = "/change_password")
    public Result<User> changePassword(String originalPwd, String newPwd, String repeatPwd) {
        return this.userService.changePassword(originalPwd, newPwd, repeatPwd);
    }

    @RequestMapping(value = "/login")
    public Result login(@RequestBody User user) {
        return this.userService.login(user);
    }

}
