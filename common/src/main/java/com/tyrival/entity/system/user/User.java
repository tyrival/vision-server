package com.tyrival.entity.system.user;

import com.tyrival.entity.base.Base;
import com.tyrival.enums.system.user.UserStateEnum;
import com.google.gson.Gson;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public class User extends Base {

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号状态 {@link UserStateEnum }
     */
    private UserStateEnum userStateEnum;

    /**
     * 电话
     */
    private String tel;

    /**
     * email
     */
    private String email;

    /**
     * 编号
     */
    private Integer code;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStateEnum getUserStateEnum() {
        return userStateEnum;
    }

    public void setUserStateEnum(UserStateEnum userStateEnum) {
        this.userStateEnum = userStateEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
