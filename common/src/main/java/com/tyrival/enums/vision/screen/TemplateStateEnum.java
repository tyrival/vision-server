package com.tyrival.enums.vision.screen;

import com.tyrival.enums.BaseEnum;

public enum TemplateStateEnum implements BaseEnum {

    ON(1, "应用模型"),
    OFF(0, "自定义模型");

    private int code;
    private String msg;

    TemplateStateEnum(int code, String msg) {
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
        return "TemplateStateEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}