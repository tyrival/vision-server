package com.tyrival.exceptions;

import java.io.Serializable;

import static com.tyrival.exceptions.ExpCodePrefix.*;


/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public enum ExceptionEnum implements Serializable {

    /**
     * 通用异常
     */
    UNKNOW_ERROR(COMMON_EXP_PREFIX + "000", "未知异常"),
    ERROR_404(COMMON_EXP_PREFIX + "001", "服务404"),
    PARAM_NULL(COMMON_EXP_PREFIX + "002", "参数为空"),
    NO_REPEAT(COMMON_EXP_PREFIX + "003", "请勿重复提交"),
    TOKEN_NULL(COMMON_EXP_PREFIX + "004", "TOKEN不存在"),
    TOKEN_EXPIRED(COMMON_EXP_PREFIX + "005", "TOKEN超时"),
    TOKEN_ERROR(COMMON_EXP_PREFIX + "006", "TOKEN错误"),
    HTTP_REQ_METHOD_ERROR(COMMON_EXP_PREFIX + "007", "HTTP请求方法不正确"),
    JSON_ERROR(COMMON_EXP_PREFIX + "008", "JSON解析异常"),
    NO_PERMISSION(COMMON_EXP_PREFIX + "009", "当前用户无此操作权限"),
    SAVE_FAIL(COMMON_EXP_PREFIX + "010", "保存失败"),
    CREATE_FAIL(COMMON_EXP_PREFIX + "011", "新增失败"),
    UPDATE_FAIL(COMMON_EXP_PREFIX + "012", "更新失败"),
    DELETE_FAIL(COMMON_EXP_PREFIX + "013", "删除失败"),
    QUERY_FAIL(COMMON_EXP_PREFIX + "014", "查询失败"),
    CLASS_CONVERT_ERROR(COMMON_EXP_PREFIX + "015", "类型转换错误"),
    URL_ENCODE_ERROR(COMMON_EXP_PREFIX + "901", "URL编码失败"),
    FILE_NOT_FOUND(COMMON_EXP_PREFIX + "902", "未找到文件"),
    FILE_TYPE_ERROR(COMMON_EXP_PREFIX + "903", "文件类型错误"),
    EXCEL_READ_FAIL(COMMON_EXP_PREFIX + "904", "Excel文件读取失败"),
    EXCEL_FIRST_ROW_EMPTY(COMMON_EXP_PREFIX + "905", "Excel文件第一行为空"),

    /**
     * Feign模块异常
     */
    HYSTRIX(FEIGN_EXP_PREFIX + "000", "服务暂时失效，请重试"),

    /**
     * Redis模块异常
     */
    REDIS_GET_ERROR(REDIS_EXP_PREFIX + "000", "Redis读取失败"),
    REDIS_SET_ERROR(REDIS_EXP_PREFIX + "001", "Redis存储失败"),
    REDIS_REMOVE_ERROR(REDIS_EXP_PREFIX + "002", "Redis删除失败"),
    REDIS_EXISTS_ERROR(REDIS_EXP_PREFIX + "003", "Redis校验失败"),

    /**
     * Elastic模块异常
     */
    ELASTIC_CREATE_FAIL(ELASTIC_EXP_PREFIX + "001", "Elastic新增失败"),
    ELASTIC_UPDATE_FAIL(ELASTIC_EXP_PREFIX + "002", "Elastic更新失败"),
    ELASTIC_DELETE_FAIL(ELASTIC_EXP_PREFIX + "003", "Elastic删除失败"),
    ELASTIC_QUERY_FAIL(ELASTIC_EXP_PREFIX + "004", "Elastic查询失败"),
    ELASTIC_OPERATE_FAIL(ELASTIC_EXP_PREFIX + "005", "Elastic操作失败"),
    ELASTIC_NOT_JSON(ELASTIC_EXP_PREFIX + "006", "Elastic存储对象类型错误"),
    ELASTIC_ID_NULL(ELASTIC_EXP_PREFIX + "007", "Elastic存储对象ID为空"),

    /**
     * 动态数据源模块异常
     */
    DATASOURCE_NOT_FOUND(DATASOURCE_EXP_PREFIX + "001", "未找到动态数据源"),
    DYNAMIC_QUERY_FAIL(DATASOURCE_EXP_PREFIX + "002", "数据查询失败"),
    NOT_DB_SOURCE(DATASOURCE_EXP_PREFIX + "003", "此数据源不是数据库"),
    QUERY_DB_FAIL(DATASOURCE_EXP_PREFIX + "004", "查询数据库失败"),
    API_PROXY_FAIL(DATASOURCE_EXP_PREFIX + "005", "请求代理失败"),
    URL_NULL(DATASOURCE_EXP_PREFIX + "006", "URL为空"),

    /**
     * User模块异常
     */
    ACCOUNT_NULL(USER_EXP_PREFIX + "000", "用户名为空"),
    USER_NULL(USER_EXP_PREFIX + "001", "用户不存在"),
    USER_INVALID(USER_EXP_PREFIX + "002", "用户失效"),
    PASSWORD_NULL(USER_EXP_PREFIX + "011", "密码为空"),
    PASSWORD_ERROR(USER_EXP_PREFIX + "012", "密码错误"),
    PASSWORD_CHANGE_FAIL(USER_EXP_PREFIX + "013", "密码修改失败"),
    PASSWORD_DIFFERENT(USER_EXP_PREFIX + "014", "两次输入的密码不一致"),

    /**
     * Attachment模块异常
     */
    UPLOAD_FAIL(ATTACHMENT_EXP_PREFIX + "000", "文件上传失败"),

    /**
     * Vision - Screen模块异常
     */
    SCREEN_PUBLISH_FAIL(VISION_EXP_PREFIX + "001", "发布可视化失败"),
    SCREEN_PREVIEW_FAIL(VISION_EXP_PREFIX + "002", "预览可视化失败");


    private String code;
    private String message;

    ExceptionEnum() {
    }

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
