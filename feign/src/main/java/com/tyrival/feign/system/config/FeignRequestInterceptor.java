package com.tyrival.feign.system.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/28
 * @Version: V1.0
 */
public class FeignRequestInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    template.header(name, value);
                }
            }
        }
    }

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }
}