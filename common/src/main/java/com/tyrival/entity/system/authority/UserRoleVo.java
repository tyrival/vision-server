package com.tyrival.entity.system.authority;

import com.tyrival.entity.system.user.User;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/30
 * @Version: V1.0
 */
public class UserRoleVo extends User {

    /**
     * 角色名
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
