package com.tyrival.enums.vision.screen;

import com.tyrival.enums.BaseEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public enum DynamicComponentTypeEnum implements BaseEnum {

    CHART(0, "DynamicChart"),
    TABLE(0, "DynamicTable"),
    PLUGIN(0, "DynamicPlugin"),
    MAP(0, "DynamicMap"),
    ;

    private int code;
    private String msg;

    DynamicComponentTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "DynamicComponentTypeEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
