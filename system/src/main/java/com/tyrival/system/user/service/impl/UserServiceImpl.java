package com.tyrival.system.user.service.impl;

import com.tyrival.entity.system.user.User;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.impl.BaseServiceImpl;
import com.tyrival.enums.system.user.UserStateEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.system.user.dao.UserDAO;
import com.tyrival.system.user.service.UserService;
import com.tyrival.utils.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public BaseDAO<User> getDAO() {
        return userDAO;
    }

    @Override
    public void beforeCreate(User user) {
        user.setUserStateEnum(UserStateEnum.ON);
    }

    @Override
    public boolean checkPassword(User user) {
        if (StringUtils.isEmpty(user.getAccount())) {
            throw new CommonException(ExceptionEnum.ACCOUNT_NULL);
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new CommonException(ExceptionEnum.PASSWORD_NULL);
        }
        String pwd = EncryptUtil.handler(user.getPassword());
        User dbUser = this.userDAO.getByAccount(user.getAccount());
        if (dbUser == null) {
            throw new CommonException(ExceptionEnum.USER_NULL);
        }
        if (!pwd.equals(dbUser.getPassword())) {
            throw new CommonException(ExceptionEnum.PASSWORD_ERROR);
        }
        return true;
    }

    @Override
    public User getByAccount(String account) {
        return this.userDAO.getByAccount(account);
    }

}
