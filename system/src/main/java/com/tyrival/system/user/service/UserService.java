package com.tyrival.system.user.service;

import com.tyrival.entity.system.user.User;
import com.tyrival.api.base.service.BaseService;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public interface UserService extends BaseService<User> {

    boolean checkPassword(User user);

    User getByAccount(String account);

    User getByCode(Integer code);
}
