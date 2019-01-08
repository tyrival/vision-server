package com.tyrival.coder.util;

import com.tyrival.coder.entity.JavaBean;

import java.io.File;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/31
 * @Version: V1.0
 */
public class PathUtil {
    
    private final static String SEPARATOR = "/";
    private final static String TEMPLATE_ROOT = "coder/src/main/resources/template/";
    private final static String MODULE_SQL_ROOT = "sql/";
    private final static String COMMON_RELATIVE = "common/src/main/java/com/tyrival/entity/";
    private final static String FEIGN_RELATIVE = "feign/src/main/java/com/tyrival/feign/";
    private final static String MODULE_RELATIVE = "/src/main/java/com/tyrival/";
    private final static String MYBATIS_RELATIVE = "/src/main/resources/mybatis/mapper/";

    public static String getProjectRoot() {
        return (new File("")).getAbsolutePath() + SEPARATOR;
    }

    public static String getModule(JavaBean javaBean) {
        return new StringBuilder(getProjectRoot())
                .append(javaBean.getModuleName())
                .append(MODULE_RELATIVE)
                .append(javaBean.getModuleName()).append(SEPARATOR)
                .append(javaBean.getPackagePath()).append(SEPARATOR).toString();
    }

    public static String getFeign(JavaBean javaBean) {
        return new StringBuilder(getProjectRoot())
                .append(FEIGN_RELATIVE)
                .append(javaBean.getModuleName()).append(SEPARATOR)
                .append(javaBean.getPackagePath()).append(SEPARATOR).toString();
    }

    public static String getCommon(JavaBean javaBean) {
        return new StringBuilder(getProjectRoot())
                .append(COMMON_RELATIVE)
                .append(javaBean.getModuleName()).append(SEPARATOR)
                .append(javaBean.getPackagePath()).append(SEPARATOR).toString();
    }

    public static String getMybatis(JavaBean javaBean) {
        return new StringBuilder(getProjectRoot())
                .append(javaBean.getModuleName())
                .append(MYBATIS_RELATIVE)
                .append(javaBean.getPackagePath()).append(SEPARATOR).toString();
    }

    public static String getSql(JavaBean javaBean) {
        return new StringBuilder(getProjectRoot())
                .append(MODULE_SQL_ROOT)
                .append(javaBean.getModuleName()).append(SEPARATOR).toString();
    }

    public static String getTemplateRoot() {
        return new StringBuilder(getProjectRoot())
                .append(TEMPLATE_ROOT).toString();
    }
}
