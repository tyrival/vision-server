package com.tyrival.enums.base;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/20
 * @Version: V1.0
 */
public enum ServerEnum {

    SYSTEM(HttpProtocolEnum.HTTP, "system"),
    DATA_SOURCE(HttpProtocolEnum.HTTP, "datasource"),
    ELASTIC(HttpProtocolEnum.HTTP, "elastic"),
    VISION(HttpProtocolEnum.HTTP, "vision");

    private HttpProtocolEnum protocol;
    private String name;

    ServerEnum(HttpProtocolEnum protocol, String name) {
        this.protocol = protocol;
        this.name = name;
    }

    public String getName() {
        return this.protocol.getProtocol() + "://server-" + this.name;
    }
}
