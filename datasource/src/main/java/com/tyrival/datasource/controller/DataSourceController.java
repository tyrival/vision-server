package com.tyrival.datasource.controller;

import com.google.gson.Gson;
import com.tyrival.datasource.service.DataSourceService;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.base.Token;
import com.tyrival.entity.datasource.DataSource;
import com.tyrival.entity.datasource.DataSourceQuery;
import com.tyrival.entity.datasource.api.ApiSource;
import com.tyrival.entity.datasource.db.Database;
import com.tyrival.entity.datasource.file.FileSource;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.entity.http.HttpResponse;
import com.tyrival.entity.system.user.User;
import com.tyrival.enums.base.BaseStateEnum;
import com.tyrival.enums.base.HttpMethodEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.utils.HttpUtil;
import com.tyrival.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping("/save_database")
    public Result saveDatabase(@RequestBody Database database) {
        try {
            return new Result(this.save(database));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.SAVE_FAIL));
        }
    }

    @RequestMapping("/save_api")
    public Result saveApi(@RequestBody ApiSource apiSource) {
        try {
            return new Result(this.save(apiSource));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.SAVE_FAIL));
        }
    }

    @RequestMapping("/save_file")
    public Result saveFile(@RequestBody FileSource fileSource) {
        try {
            return new Result(this.save(fileSource));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.SAVE_FAIL));
        }
    }

    public <T extends DataSource> T save(T t) {
        User user = this.getCurrentUser();
        String userId = user == null ? null : user.getId();
        int i = 0;
        if (StringUtils.isEmpty(t.getId())) {
            t.setId(UUID.randomUUID().toString());
            t.setCreateTime(new Date());
            t.setCreateUserId(userId);
            t.setDelFlag(BaseStateEnum.ACTIVE);
            i = this.dataSourceService.create(t);
        } else {
            t.setUpdateUserId(userId);
            t.setUpdateTime(new Date());
            i = this.dataSourceService.update(t);
        }
        return i > 0 ? t : null;
    }

    @RequestMapping("/delete")
    public Result delete(@RequestBody DataSourceQuery query) {
        try {
            return new Result(this.dataSourceService.delete(query));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.DELETE_FAIL));
        }
    }

    @RequestMapping("/get")
    public Result get(@RequestBody DataSourceQuery query) {
        try {
            return new Result(this.dataSourceService.get(query));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping("/list_by_user")
    public Result listByUser() {
        try {
            User user = this.getCurrentUser();
            if (user == null) {
                return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
            }
            DataSourceQuery query = new DataSourceQuery();
            query.setUserId(user.getId());
            return new Result(this.dataSourceService.listByUser(query));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping("/list_db_by_user")
    public Result listDbByUser() {
        try {
            User user = this.getCurrentUser();
            if (user == null) {
                return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
            }
            DataSourceQuery query = new DataSourceQuery();
            query.setUserId(user.getId());
            return new Result(this.dataSourceService.listDbByUser(query));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping("/list_file_by_user")
    public Result listFileByUser() {
        try {
            User user = this.getCurrentUser();
            if (user == null) {
                return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
            }
            DataSourceQuery query = new DataSourceQuery();
            query.setUserId(user.getId());
            return new Result(this.dataSourceService.listFileByUser(query));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_FAIL));
        }
    }

    @RequestMapping("/query")
    public Result query(@RequestBody DataSourceQuery query) {
        try {
            List<Map<String, Object>> list = this.dataSourceService.query(query);
            return new Result(list);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.DYNAMIC_QUERY_FAIL));
        }
    }

    @RequestMapping("/list_db")
    public Result listDb(@RequestBody Database database) {
        try {
            return new Result(this.dataSourceService.listDb(database));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.QUERY_DB_FAIL));
        }
    }

    @RequestMapping("/api_proxy")
    public Result apiProxy(@RequestBody HttpRequest request) {
        try {
            String url = request.getUrl();
            if (StringUtils.isEmpty(url)) {
                return new Result(new CommonException(ExceptionEnum.URL_NULL));
            }
            HttpResponse response = HttpUtil.proxy(request);
            return new Result(response.getBody());
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.API_PROXY_FAIL));
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
