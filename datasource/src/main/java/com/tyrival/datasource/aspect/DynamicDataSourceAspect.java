package com.tyrival.datasource.aspect;

import com.tyrival.datasource.config.DataSourceHolder;
import com.tyrival.entity.datasource.db.Database;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/12
 * @Version: V1.0
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("execution(public * com.tyrival..*.*(..)) && @annotation(com.tyrival.datasource.annotation.DynamicDatabase)")
    public void dynamicPoint() {
    }

    // 获取请求
    @Before("dynamicPoint()")
    public void dynamicDataSource(JoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (args.length <= 0) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            if (!(args[i] instanceof Database)) {
                continue;
            }
            DataSourceHolder.setDataSource((Database) args[0]);
            return;
        }
    }

    @After("dynamicPoint()")
    public void clearDataSource(JoinPoint point) {
        DataSourceHolder.clear();
    }
}
