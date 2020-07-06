package com.tyrival.system.user.controller;

import com.tyrival.config.ControllerConfig;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.base.Token;
import com.tyrival.entity.system.user.User;
import com.tyrival.api.base.service.BaseService;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.system.user.service.UserService;
import com.tyrival.api.base.controller.BaseController;
import com.tyrival.utils.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/23
 * @Version: V1.0
 */
@RestController
@RequestMapping(ControllerConfig.USER)
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Override
    public BaseService getService() {
        return userService;
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        try {
            boolean flag = this.userService.checkPassword(user);
            if (flag) {
                user = this.userService.getByAccount(user.getAccount());
                return new Result(Token.generate(user));
            } else {
                return new Result(ExceptionEnum.PASSWORD_ERROR);
            }
        } catch (CommonException e) {
            return new Result(e);
        } catch (Exception e) {
            return new Result(ExceptionEnum.UNKNOW_ERROR);
        }

    }

    @RequestMapping("/change_password")
    public Result changePassword(String originalPwd, String newPwd, String repeatPwd) {
        try {
            if (StringUtils.isEmpty(newPwd)) {
                return new Result(ExceptionEnum.PASSWORD_NULL);
            }
            if (!newPwd.equals(repeatPwd)) {
                return new Result(ExceptionEnum.PASSWORD_DIFFERENT);
            }
            User user = this.getCurrentUser();
            user = this.userService.get(user.getId());
            user.setPassword(originalPwd);
            boolean check = this.userService.checkPassword(user);
            if (check) {
                user.setPassword(EncryptUtil.handler(newPwd));
                user = this.userService.update(user);
                return new Result();
            }
            return new Result(ExceptionEnum.UNKNOW_ERROR);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @RequestMapping("/current")
    public Result current() {
        return new Result<>(this.getCurrentUser());
    }

    @GetMapping("/get_by_code")
    public Result getByCode(@RequestParam Integer code) {
        try {
            User user = this.userService.getByCode(code);
            if (user == null) {
                return new Result(ExceptionEnum.USER_NULL);
            }
            return new Result(user);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @GetMapping("/token/temp")
    public Result temporaryToken(@RequestParam String id, Long expireTime) {
        try {
            User user = this.userService.get(id);
            if (user == null) {
                return new Result(ExceptionEnum.USER_NULL);
            }
            String token = Token.generate(user, expireTime);
            return new Result(token);
        } catch (CommonException e) {
            return new Result(e);
        }
    }

    @GetMapping("/token/persist")
    public Result persistToken(@RequestParam String id) {
        try {
            User user = this.userService.get(id);
            if (user == null) {
                return new Result(ExceptionEnum.USER_NULL);
            }
            String token = Token.persistent(user);
            return new Result(token);
        } catch (CommonException e) {
            return new Result(e);
        }
    }
}
