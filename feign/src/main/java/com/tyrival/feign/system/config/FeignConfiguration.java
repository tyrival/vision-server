package com.tyrival.feign.system.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/28
 * @Version: V1.0
 */
@Configuration
public class FeignConfiguration {

    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignRequestInterceptor();
    }

}
