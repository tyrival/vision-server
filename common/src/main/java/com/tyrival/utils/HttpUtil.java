package com.tyrival.utils;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.entity.http.HttpResponse;
import com.tyrival.entity.http.HttpsRequestFactory;
import com.tyrival.enums.base.HttpMethodEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/17
 * @Version: V1.0
 */
public class HttpUtil {

    private final static String HTTPS_PREFIX = "https";

    public static HttpResponse proxy(HttpRequest request) {
        RestTemplate restTemplate = new RestTemplate(new HttpsRequestFactory());
        if (request.getMethod() == HttpMethodEnum.GET) {
            return doGet(restTemplate, request);
        } else {
            return doPost(restTemplate, request);
        }
    }

    /**
     * HTTP GET
     * @param url
     * @return
     */
    public static HttpResponse httpGet(String url) {
        return httpGet(url, "", "");
    }

    public static HttpResponse httpGet(String url, String params) {
        return httpGet(url, "", params);
    }

    public static HttpResponse httpGet(String url, String header, String params) {
        HttpRequest request = new HttpRequest(url, HttpMethodEnum.GET, header, params);
        RestTemplate restTemplate = new RestTemplate(new HttpsRequestFactory());
        return doGet(restTemplate, request);
    }

    /**
     * HTTP POST
     * @param url
     * @return
     */
    public static HttpResponse httpPost(String url) {
        return httpPost(url, "", "");
    }

    public static HttpResponse httpPost(String url, String params) {
        return httpPost(url, "", params);
    }

    public static HttpResponse httpPost(String url, String header, String params) {
        HttpRequest request = new HttpRequest(url, HttpMethodEnum.POST, header, params);
        RestTemplate restTemplate = new RestTemplate(new HttpsRequestFactory());
        return doPost(restTemplate, request);
    }

    /**
     * HTTPS GET
     * @param url
     * @return
     */
    public static HttpResponse httpsGet(String url) {
        return httpsGet(url,"", "");
    }

    public static HttpResponse httpsGet(String url, String params) {
        return httpsGet(url,"", params);
    }

    public static HttpResponse httpsGet(String url, String header, String params) {
        HttpRequest request = new HttpRequest(url, HttpMethodEnum.GET, header, params);
        RestTemplate restTemplate = new RestTemplate(new HttpsRequestFactory());
        return doGet(restTemplate, request);
    }

    /**
     * HTTPS POST
     * @param url
     * @return
     */

    public static HttpResponse httpsPost(String url) {
        return httpsPost(url,"", "");
    }

    public static HttpResponse httpsPost(String url, String params) {
        return httpsPost(url,"", params);
    }

    public static HttpResponse httpsPost(String url, String header, String params) {
        HttpRequest request = new HttpRequest(url, HttpMethodEnum.POST, header, params);
        RestTemplate restTemplate = new RestTemplate(new HttpsRequestFactory());
        return doPost(restTemplate, request);
    }

    private static HttpResponse doGet(RestTemplate restTemplate, HttpRequest request) {
        // 解决(响应数据可能)中文乱码 的问题
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        // 移除原来的转换器
        converterList.remove(1);
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        // 添加新的转换器(注:convert顺序错误会导致失败)
        converterList.add(1, converter);
        restTemplate.setMessageConverters(converterList);

        // (选择性设置)请求头信息
        // HttpHeaders实现了MultiValueMap接口
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> headers = request.getHeadersMap();
        // 给请求header中添加一些数据
        if (!headers.isEmpty()) {
            for (String key : headers.values()) {
                httpHeaders.add(key, headers.get(key));
            }
        }
        // 注:GET请求 创建HttpEntity时,请求体传入null即可
        // 请求体的类型任选即可;只要保证 请求体 的类型与HttpEntity类的泛型保持一致即可
        String httpBody = null;
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpBody, httpHeaders);

        try {
            // URI
            StringBuffer paramsURL = new StringBuffer(request.getUrl());
            // 字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)
            paramsURL.append("?flag=" + URLEncoder.encode("&", "utf-8"));
            Map<String, String> paramsMap = request.getParamsMap();
            if (!paramsMap.isEmpty()) {
                for (String key : paramsMap.values()) {
                    paramsURL.append(URLEncoder.encode("&", "utf-8"))
                            .append(URLEncoder.encode(key))
                            .append("=")
                            .append(URLEncoder.encode(paramsMap.get(key)));
                }
            }
            URI uri = URI.create(paramsURL.toString());

            // 执行请求并返回结果
            // 此处的泛型  对应 响应体数据   类型;即:这里指定响应体的数据装配为String
            ResponseEntity<String> response =
                    restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

            Gson gson = new Gson();
            HttpResponse resp = new HttpResponse(response.getStatusCodeValue(),
                    gson.toJson(response.getHeaders()),
                    response.getBody());
            return resp;
        } catch (UnsupportedEncodingException e) {
            throw new CommonException(ExceptionEnum.URL_ENCODE_ERROR);
        }
    }

    private static HttpResponse doPost(RestTemplate restTemplate, HttpRequest request) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.remove(1); // 移除原来的转换器
        // 设置字符编码为utf-8
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter); // 添加新的转换器(注:convert顺序错误会导致失败)
        restTemplate.setMessageConverters(converterList);

        // (选择性设置)请求头信息
        // HttpHeaders实现了MultiValueMap接口
        HttpHeaders httpHeaders = new HttpHeaders();
        // 设置contentType
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 给请求header中添加一些数据
        Map<String, String> headers = request.getHeadersMap();
        // 给请求header中添加一些数据
        if (!headers.isEmpty()) {
            for (String key : headers.values()) {
                httpHeaders.add(key, headers.get(key));
            }
        }

        // 将请求头、请求体数据，放入HttpEntity中
        // 请求体的类型任选即可;只要保证 请求体 的类型与HttpEntity类的泛型保持一致即可
        // 这里手写了一个json串作为请求体 数据 (实际开发时,可使用fastjson、gson等工具将数据转化为json串)
        HttpEntity<String> httpEntity = new HttpEntity<String>(request.getParams(), httpHeaders);

        try {
            // URI
            StringBuffer paramsURL = new StringBuffer(request.getUrl());
            // 字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)
            paramsURL.append("?flag=" + URLEncoder.encode("&", "utf-8"));
            URI uri = URI.create(paramsURL.toString());

            // 执行请求并返回结果
            // 此处的泛型  对应 响应体数据   类型;即:这里指定响应体的数据装配为String
            ResponseEntity<String> response =
                    restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);

            Gson gson = new Gson();
            HttpResponse resp = new HttpResponse(response.getStatusCodeValue(),
                    gson.toJson(response.getHeaders()),
                    response.getBody());
            return resp;
        } catch (
                UnsupportedEncodingException e) {
            throw new CommonException(ExceptionEnum.URL_ENCODE_ERROR);
        }
    }

}
