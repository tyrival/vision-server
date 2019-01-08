package com.tyrival.api.base.controller;

import com.tyrival.entity.base.Base;
import com.tyrival.entity.base.Parameter;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.base.Token;
import com.tyrival.entity.system.user.User;
import com.tyrival.enums.base.BaseStateEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.api.base.service.BaseService;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/25
 * @Version: V1.0
 */
public abstract class BaseController<T> {

    public abstract BaseService getService();

    @RequestMapping("/save")
    public Result save(@RequestBody T t) {
        try {
            if (!(t instanceof Base)) {
                return new Result(new CommonException(ExceptionEnum.CLASS_CONVERT_ERROR));
            }
            Base base = (Base) t;
            User user = this.getCurrentUser();
            String userId = user == null ? null : user.getId();
            if (StringUtils.isEmpty(base.getId())) {
                base.setId(UUID.randomUUID().toString());
                base.setCreateTime(new Date());
                base.setCreateUserId(userId);
                base.setDelFlag(BaseStateEnum.ACTIVE);
                t = (T) this.getService().create(base);
            } else {
                base.setUpdateUserId(userId);
                base.setUpdateTime(new Date());
                t = (T) this.getService().update(t);
            }
            return new Result(t);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.SAVE_FAIL));
        }
    }

    @RequestMapping("/create")
    public Result create(@RequestBody T t) {
        try {
            t = (T) this.getService().create(t);
            User user = this.getCurrentUser();
            if (user != null && t instanceof Base) {
                Base base = (Base) t;
                base.setCreateUserId(user.getId());
                return new Result(base);
            }
            return new Result(t);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.CREATE_FAIL));
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody T t) {
        try {
            t = (T) this.getService().update(t);
            User user = this.getCurrentUser();
            if (user != null && t instanceof Base) {
                Base base = (Base) t;
                base.setUpdateUserId(user.getId());
                return new Result(base);
            }
            return new Result(t);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.UPDATE_FAIL));
        }
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam("id") String id) {
        try {
            int i = this.getService().delete(id);
            Result result = new Result();
            result.setSuccess(i > 0);
            return result;
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.DELETE_FAIL));
        }
    }

    @RequestMapping("/get")
    public Result get(@RequestParam("id") String id) {
        try {
            T t = (T) this.getService().get(id);
            return new Result(t);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping(value = "/list")
    public Result list(Parameter parameter) {
        try {
            List<T> list = this.getService().list(parameter);
            return new Result(list);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping("/list_by_page")
    public Result listByPage(@RequestBody Parameter parameter) {
        try {
            Result result = this.getService().listByPage(parameter);
            return result;
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected User getCurrentUser() {
        String userString = this.getRequest().getHeader(Token.REQUEST_ATTRIBUTE_USER);
        Gson gson = new Gson();
        return gson.fromJson(userString, User.class);
    }
}
