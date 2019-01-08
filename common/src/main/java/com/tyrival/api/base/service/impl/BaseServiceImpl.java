package com.tyrival.api.base.service.impl;

import com.tyrival.entity.base.*;
import com.tyrival.enums.base.BaseStateEnum;
import com.tyrival.api.base.dao.BaseDAO;
import com.tyrival.api.base.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/26
 * @Version: V1.0
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    public abstract BaseDAO<T> getDAO();

    public void beforeCreate(T t){};
    public void afterCreate(T t) {};
    public void beforeUpdate(T t){};
    public void afterUpdate(T t){};
    public void beforeDelete(T t){};
    public void afterDelete(T t){};

    @Override
    public T create(T t) {
        this.beforeCreate(t);
        int i = this.getDAO().insert(t);
        this.afterCreate(t);
        return i > 0 ? t : null;
    }

    @Override
    public T update(T t) {
        this.beforeUpdate(t);
        int i = this.getDAO().update(t);
        this.afterUpdate(t);
        return i > 0 ? t : null;
    }

    @Override
    public int delete(String id) {
        T t = this.get(id);
        if (t instanceof Base) {
            Base base = (Base) t;
            base.setUpdateTime(new Date());
            base.setDelFlag(BaseStateEnum.DELETE);
            this.beforeDelete(t);
            int i = this.getDAO().update(t);
            this.afterDelete(t);
            return i;
        } else {
            this.beforeDelete(t);
            int i = this.getDAO().delete(id);
            this.afterDelete(t);
            return i;
        }
    }

    @Override
    public T get(String id) {
        return this.getDAO().get(id);
    }

    @Override
    public List<T> list() {
        return this.list(null);
    }

    @Override
    public List<T> list(Parameter parameter) {
        QueryParam queryParam = new QueryParam(parameter);
        return this.getDAO().list(queryParam);
    }

    @Override
    public Result listByPage(Parameter parameter) {
        QueryParam queryParam = new QueryParam(parameter);
        PageInfo pageInfo =
                PageHelper.startPage(queryParam.getParameter().getPageIndex(),
                        queryParam.getParameter().getPageSize())
                .doSelectPageInfo(() -> this.getDAO().list(queryParam));
        Page page = new Page();
        page.setPageIndex(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalCount(pageInfo.getTotal());
        return new Result(pageInfo.getList(), page);
    }
}
