package com.tyrival.entity.system.role;

import com.tyrival.entity.base.Base;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/30
 * @Version: V1.0
 */
public class Role extends Base {

    /**
     * 角色名
     */
    private String name;

    /**
     * 说明
     */
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
