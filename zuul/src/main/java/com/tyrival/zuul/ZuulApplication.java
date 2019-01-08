package com.tyrival.zuul;

import com.tyrival.entity.base.Token;
import com.tyrival.zuul.filter.ErrorFilter;
import com.tyrival.zuul.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
public class ZuulApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    /**
     * 跨域拦截器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // #允许向该服务器提交请求的URI，*表示全部允许，
        // 在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedOrigin("*");
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader("*");
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(1800L);
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("*");
        config.addExposedHeader(Token.REQUEST_ATTRIBUTE_TOKEN);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(
                new org.springframework.web.filter.CorsFilter(source));
        bean.setOrder(-999);
        return bean;
    }

}
