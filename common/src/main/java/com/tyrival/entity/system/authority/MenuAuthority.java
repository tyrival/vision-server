package com.tyrival.entity.system.authority;

import com.tyrival.entity.base.Base;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/30
 * @Version: V1.0
 */
public class MenuAuthority extends Base implements Serializable {

    /**
     * 所有者ID，角色或用户
     */
    private String ownerId;

    /**
     * 菜单ID
     */
    private String menuId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
