package com.tyrival.entity.base;

import com.tyrival.exceptions.CommonException;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/5/9
 * @Version: V1.0
 */
public class Result<T> implements Serializable {
    private Boolean success;

    private String errorCode;

    private String message;

    private Page page;

    private T data;

    public Result() {
        this.success = true;
    }

    public Result(Boolean success) {
        this.success = success;
    }

    public Result(T data) {
        this.success = true;
        this.data = data;
    }

    public Result(T data, Page page) {
        this.success = true;
        this.page = page;
        this.data = data;
    }

    public Result(CommonException exception) {
        this.success = false;
        this.errorCode = exception.getCodeEnum().getCode();
        this.message = exception.getCodeEnum().getMessage();
    }

    public Result(T data, CommonException exception) {
        this.success = false;
        this.data = data;
        this.errorCode = exception.getCodeEnum().getCode();
        this.message = exception.getCodeEnum().getMessage();
    }

    public Result(T data, Page page, CommonException exception) {
        this.success = false;
        this.data = data;
        this.page = page;
        this.errorCode = exception.getCodeEnum().getCode();
        this.message = exception.getCodeEnum().getMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
