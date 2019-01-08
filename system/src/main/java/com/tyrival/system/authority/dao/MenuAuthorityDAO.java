package com.tyrival.system.authority.dao;

import com.tyrival.entity.system.authority.*;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.entity.system.authority.MenuAuthority;
import com.tyrival.entity.system.user.User;
import com.tyrival.entity.system.authority.MenuAuthorityVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MenuAuthorityDAO extends BaseDAO<MenuAuthority> {

    List<MenuAuthorityVo> listByUser(User user);
}
