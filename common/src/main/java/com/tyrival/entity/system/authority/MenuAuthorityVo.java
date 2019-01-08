package com.tyrival.entity.system.authority;

import com.tyrival.entity.system.menu.Menu;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/30
 * @Version: V1.0
 */
public class MenuAuthorityVo extends Menu {

    /**
     * 所有者ID
     */
    private String ownerId;

    /**
     * 所有者名称
     */
    private String ownerName;


    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
