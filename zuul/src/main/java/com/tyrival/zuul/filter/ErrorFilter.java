package com.tyrival.zuul.filter;

import com.tyrival.exceptions.CommonException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/28
 * @Version: V1.0
 */
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable().getCause();
        if (throwable instanceof CommonException) {
            CommonException exception = (CommonException) throwable;
            ZuulException zuulException = (ZuulException) ctx.getThrowable();
            zuulException.nStatusCode = Integer.parseInt(exception.getCodeEnum().getCode());
            zuulException.errorCause = exception.getMessage();
            ctx.set("throwable", zuulException);
        }
        return null;
    }
}