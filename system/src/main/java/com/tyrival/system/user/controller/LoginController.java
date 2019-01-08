package com.tyrival.system.user.controller;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.system.user.User;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/31
 * @Version: V1.0
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        try {
            boolean flag = this.userService.checkPassword(user);
            if (flag) {
                return new Result();
            } else {
                return new Result(new CommonException(ExceptionEnum.PASSWORD_ERROR));
            }
        } catch (Exception e) {
            return new Result(e);
        }
    }
}
