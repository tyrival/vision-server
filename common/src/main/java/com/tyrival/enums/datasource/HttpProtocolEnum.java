package com.tyrival.enums.datasource;

import com.tyrival.enums.BaseEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
public enum HttpProtocolEnum implements BaseEnum {

    HTTP(0, "http协议"),
    HTTPS(1, "https协议");

    private int code;
    private String msg;

    HttpProtocolEnum(int code, String msg) {
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
        return "HttpProtocolEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
