package com.tyrival.entity.http;

import com.google.gson.Gson;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/17
 * @Version: V1.0
 */
public class HttpResponse<T> {

    private Integer statusCode;

    private String header;

    private T body;

    public HttpResponse(Integer statusCode, String header, T body) {
        this.statusCode = statusCode;
        this.header = header;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getHeader() {
        return header;
    }

    public T getBody() {
        return body;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
