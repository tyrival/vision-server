package com.tyrival.entity.datasource.api;

import com.tyrival.entity.datasource.DataSource;
import com.tyrival.enums.datasource.HttpProtocolEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
public class ApiSource extends DataSource {

    private String url;

    private HttpProtocolEnum protocol;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpProtocolEnum getProtocol() {
        return protocol;
    }

    public void setProtocol(HttpProtocolEnum protocol) {
        this.protocol = protocol;
    }
}
