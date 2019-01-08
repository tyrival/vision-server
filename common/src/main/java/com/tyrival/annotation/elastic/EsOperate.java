package com.tyrival.annotation.elastic;

import com.tyrival.enums.elastic.EsOperateEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/30
 * @Version: V1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EsOperate {

    /**
     * elastic index
     */
    @AliasFor("index")
    String index();

    /**
     * elastic type
     */
    @AliasFor("value")
    String type();

    /**
     * 是否异步，默认是
     */
    @AliasFor("async")
    boolean async() default true;

    /**
     * id字段，默认"id"
     */
    @AliasFor("id")
    String id() default "id";

    /**
     * 操作
     */
    @AliasFor("operate")
    EsOperateEnum operate();

}
