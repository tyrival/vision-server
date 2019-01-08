package com.tyrival.enums.base;

import com.tyrival.enums.BaseEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/26
 * @Version: V1.0
 */
public enum BaseStateEnum implements BaseEnum {

    ACTIVE(0, "有效"),
    DELETE(1, "删除");

    private int code;
    private String msg;

    BaseStateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "BaseStateEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}