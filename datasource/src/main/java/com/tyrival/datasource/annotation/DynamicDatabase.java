package com.tyrival.datasource.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatabase {
}