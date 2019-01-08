package com.tyrival.entity.http;

import com.google.gson.Gson;
import com.tyrival.enums.base.HttpMethodEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/17
 * @Version: V1.0
 */
public class HttpRequest {

    /**
     * url
     */
    private String url;
    /**
     * method
     */
    private HttpMethodEnum method;
    /**
     * 头信息
     */
    private String headers;
    /**
     * 参数
     */
    private String params;
    /**
     * 头信息解析
     */
    private Map<String, String> headersMap;
    /**
     * 参数解析
     */
    private Map<String, String> paramsMap;

    public HttpRequest() {
        this.headers = "";
        this.params = "";
        this.headersMap = new HashMap<>();
        this.paramsMap = new HashMap<>();
    }

    public HttpRequest(String url) {
        this(url, HttpMethodEnum.GET, "", "");
    }

    public HttpRequest(String url, HttpMethodEnum method) {
        this(url, method, "", "");
    }

    public HttpRequest(String url, String params) {
        this(url, HttpMethodEnum.GET, "", params);
    }

    public HttpRequest(String url, HttpMethodEnum method, String params) {
        this(url, method, "", params);
    }

    public HttpRequest(String url, HttpMethodEnum method, String headers, String params) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
        Gson gson = new Gson();
        this.headersMap = gson.fromJson(headers, Map.class);
        this.paramsMap = gson.fromJson(params, Map.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(HttpMethodEnum method) {
        this.method = method;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    public void setHeadersMap(Map<String, String> headersMap) {
        this.headersMap = headersMap;
    }

    public Map<String, String> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, String> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
        Gson gson = new Gson();
        this.headersMap = gson.fromJson(headers, Map.class);
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
        Gson gson = new Gson();
        this.paramsMap = gson.fromJson(params, Map.class);
    }
}
