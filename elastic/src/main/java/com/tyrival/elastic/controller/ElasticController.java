package com.tyrival.elastic.controller;

import com.tyrival.elastic.service.ElasticService;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.elastic.OperateParam;
import com.tyrival.entity.elastic.SearchParam;
import com.tyrival.enums.elastic.EsOperateEnum;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/31
 * @Version: V1.0
 */
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private ElasticService elasticService;

    /**
     * 无回调函数的异步监听
     */
    private ActionListener EMPTY_LISTENER = new ActionListener() {
        @Override
        public void onResponse(Object o) {}
        @Override
        public void onFailure(Exception e) {}
    };

    /**
     * 全文检索
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public Result search(@RequestBody SearchParam param) {
        try {
            return new Result(this.elasticService.search(param));
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.ELASTIC_QUERY_FAIL));
        }
    }

    /**
     * 代理所有增删改查方法，仅有根据id查询，全文检索需使用/search接口
     * @param param
     * @return
     */
    @RequestMapping("/operate")
    public Result operate(@RequestBody OperateParam param) {
        try {
            if (StringUtils.isEmpty(param.getId())) {
                return new Result(new CommonException(ExceptionEnum.ELASTIC_ID_NULL));
            }
            EsOperateEnum operate = param.getOperate();
            boolean async = param.getAsync();
            String id = param.getId();
            String index = param.getIndex();
            String type = param.getType();
            Object response = null;
            switch (operate) {
                case CREATE:
                    if (param.getMap() == null || param.getMap().size() <= 0) {
                        return new Result(new CommonException(ExceptionEnum.ELASTIC_NOT_JSON));
                    }
                    if (async) {
                        this.elasticService.createAsync(param.getMap(), id, index, type, EMPTY_LISTENER);
                    } else {
                        response = this.elasticService.create(param.getMap(), id, index, type);
                    }
                    break;
                case UPDATE:
                    if (param.getMap() == null || param.getMap().size() <= 0) {
                        return new Result(new CommonException(ExceptionEnum.ELASTIC_NOT_JSON));
                    }
                    if (async) {
                        this.elasticService.updateAsync(param.getMap(), id, index, type, EMPTY_LISTENER);
                    } else {
                        response = this.elasticService.update(param.getMap(),  id, index, type);
                    }
                    break;
                case DELETE:
                    if (async) {
                        this.elasticService.deleteAsync(id, index, type, EMPTY_LISTENER);
                    } else {
                        response = this.elasticService.delete(id, index, type);
                    }
                    break;
                case GET:
                    response = this.elasticService.get(id, index, type);
                    break;
                case EXIST:
                    response = this.elasticService.exist(id, index, type);
                    break;
                default:
                    break;
            }
            return response == null ? new Result() : new Result(response);
        } catch (Exception e) {
            return new Result(new CommonException(ExceptionEnum.ELASTIC_OPERATE_FAIL));
        }
    }

}
