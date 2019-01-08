package com.tyrival.aspect.elastic;

import com.google.gson.Gson;
import com.tyrival.annotation.elastic.EsOperate;
import com.tyrival.entity.base.Base;
import com.tyrival.entity.base.ReflectObject;
import com.tyrival.entity.base.Result;
import com.tyrival.entity.elastic.OperateParam;
import com.tyrival.entity.elastic.SearchParam;
import com.tyrival.entity.http.HttpRequest;
import com.tyrival.entity.http.HttpsRequestFactory;
import com.tyrival.enums.base.ApiEnum;
import com.tyrival.enums.base.HttpMethodEnum;
import com.tyrival.enums.elastic.EsOperateEnum;
import com.tyrival.utils.HttpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/12/30
 * @Version: V1.0
 */
@Aspect
@Component
public class EsOperateAspect<T> {

    @Pointcut("@annotation(com.tyrival.annotation.elastic.EsOperate)")
    public void operatePoint() {
    }

    @Before("operatePoint()&&@annotation(esOperate)")
    public void operate(JoinPoint point, EsOperate esOperate) throws Throwable {
        Object[] args = point.getArgs();
        if (args.length <= 0) {
            return;
        }
        if (!(args[0] instanceof Base)) {
            return;
        }
        T t = (T) args[0];
        String url = ApiEnum.ELASTIC_OPERATE.getUrl();
        OperateParam param = new OperateParam();
        param.setIndex(esOperate.index());
        param.setType(esOperate.type());
        param.setOperate(esOperate.operate());
        Gson gson = new Gson();
        param.setJson(gson.toJson(t));
        ReflectObject reflect = new ReflectObject(t);
        String id = (String) reflect.getMethodValue("id");
        param.setId(id);
        HttpRequest request = new HttpRequest(url, HttpMethodEnum.POST, param.toString());
        HttpUtil.proxy(request);
    }

    @After("operatePoint()")
    public void clearDataSource(JoinPoint point) {
    }
}
