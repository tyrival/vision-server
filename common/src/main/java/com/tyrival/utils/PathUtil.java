package com.tyrival.utils;

import org.springframework.util.ClassUtils;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public class PathUtil {

    public static String getRootPath() {
        return ClassUtils.getDefaultClassLoader().getResource("").getPath();
    }
}
