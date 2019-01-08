package com.tyrival.entity.base;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.tyrival.enums.base.BaseStateEnum;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public class Base implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 删除标记 0：未删除 1：已删除
     */
    private BaseStateEnum delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public BaseStateEnum getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(BaseStateEnum delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }
}
