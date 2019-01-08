package com.tyrival.zuul.user.service.impl;

import com.tyrival.entity.system.user.User;
import com.tyrival.api.feign.service.impl.FeignServiceHystrix;
import com.tyrival.zuul.user.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@Component
public class UserServiceHystrix extends FeignServiceHystrix<User> implements UserService {
}
