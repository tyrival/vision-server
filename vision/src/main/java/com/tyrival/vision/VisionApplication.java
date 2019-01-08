package com.tyrival.vision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ComponentScan 注解作用是扫描包下面的Model、Service、Controller、Aspect类
 * 如果不加的话，无法引入Aspect等组件
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.tyrival")
public class VisionApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VisionApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(VisionApplication.class, args);
    }
}
