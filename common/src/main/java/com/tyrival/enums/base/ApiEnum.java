package com.tyrival.enums.base;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/20
 * @Version: V1.0
 */
public enum ApiEnum {

    USER_GET(ServerEnum.SYSTEM, "/user/get"),
    ATTACHMENT_GET(ServerEnum.SYSTEM, "/attachment/get"),
    ATTACHMENT_DELETE(ServerEnum.SYSTEM, "/attachment/delete"),
    ELASTIC_OPERATE(ServerEnum.ELASTIC, "/elastic/operate"),
    ELASTIC_SEARCH(ServerEnum.ELASTIC, "/elastic/search");

    ApiEnum(ServerEnum server, String uri) {
        this.server = server;
        this.uri = uri;
    }

    private ServerEnum server;

    private String uri;

    public String getUrl() {
        return this.server.getName() + this.uri;
    }
}
