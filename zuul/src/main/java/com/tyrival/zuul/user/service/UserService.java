package com.tyrival.zuul.user.service;

import com.tyrival.entity.system.user.User;
import com.tyrival.api.feign.service.FeignService;
import com.tyrival.zuul.user.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/24
 * @Version: V1.0
 */
@FeignClient(value = "server-system", path="/user", fallback = UserServiceHystrix.class)
public interface UserService extends FeignService<User> {

}
