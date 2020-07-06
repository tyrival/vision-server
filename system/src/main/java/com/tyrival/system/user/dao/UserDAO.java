package com.tyrival.system.user.dao;

import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
@Mapper
@Component
public interface UserDAO extends BaseDAO<User> {

    User getByAccount(String account);

    User getByCode(Integer code);
}
