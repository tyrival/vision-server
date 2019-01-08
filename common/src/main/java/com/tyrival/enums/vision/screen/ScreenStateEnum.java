package com.tyrival.enums.vision.screen;

import com.tyrival.enums.BaseEnum;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public enum ScreenStateEnum implements BaseEnum {

    ON(1, "已发布"),
    OFF(0, "未发布");

    private int code;
    private String msg;

    ScreenStateEnum(int code, String msg) {
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
        return "ScreenStateEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
