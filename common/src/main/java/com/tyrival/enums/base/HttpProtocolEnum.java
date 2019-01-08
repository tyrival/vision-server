package com.tyrival.enums.base;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/20
 * @Version: V1.0
 */
public enum HttpProtocolEnum {

    HTTP("http"), HTTPS("https");

    HttpProtocolEnum(String protocol) {
        this.protocol = protocol;
    }

    private String protocol;

    public String getProtocol() {
        return protocol;
    }
}
