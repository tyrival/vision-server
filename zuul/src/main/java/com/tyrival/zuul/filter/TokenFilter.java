package com.tyrival.zuul.filter;

import com.tyrival.entity.base.Result;
import com.tyrival.entity.base.Token;
import com.tyrival.entity.system.user.User;
import com.tyrival.exceptions.CommonException;
import com.tyrival.exceptions.ExceptionEnum;
import com.tyrival.zuul.user.service.UserService;
import com.github.pagehelper.util.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/27
 * @Version: V1.0
 */
@RefreshScope
@ConfigurationProperties(prefix = "filter.token")
public class TokenFilter extends ZuulFilter {

    private List<String> excludeUri;

    @Autowired
    private UserService userService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        if (this.excludeUri == null || this.excludeUri.size() <= 0) {
            return true;
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        for (int i = 0; i < this.excludeUri.size(); i++) {
            String exc = this.excludeUri.get(i);
            if (StringUtils.isEmpty(exc)) {
                continue;
            }
            Pattern pattern = Pattern.compile(exc);
            Matcher matcher = pattern.matcher(uri);
            if (matcher.find()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String tokenString = request.getHeader(Token.REQUEST_ATTRIBUTE_TOKEN);
        Token token;
        try {
            token = Token.parse(tokenString);
        } catch (ExpiredJwtException ex) {
            throw new CommonException(ExceptionEnum.TOKEN_EXPIRED);
        } catch (Exception ex) {
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }
        User tokenUser = token.getUser();
        Result reply = this.userService.get(tokenUser.getId());
        User user = (User) reply.getData();
        if (user == null) {
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }
        if (!user.getAccount().equals(tokenUser.getAccount()) ||
                !user.getName().equals(tokenUser.getName())) {
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }

        // 对比持久化Token的password，判断是否失效
        String password = tokenUser.getPassword();
        if (StringUtil.isNotEmpty(password) && !password.equals(user.getPassword())) {
            throw new CommonException(ExceptionEnum.TOKEN_ERROR);
        }

        // 将用户信息注入请求头
        ctx.addZuulRequestHeader(Token.REQUEST_ATTRIBUTE_USER, user.toString());

        // 返回一个新临时Token
        if (!token.isPersistent()) {
            ctx.getResponse().addHeader(Token.REQUEST_ATTRIBUTE_TOKEN, Token.generate(user));
        } else {
            ctx.getResponse().addHeader(Token.REQUEST_ATTRIBUTE_TOKEN, tokenString);
        }
        return null;
    }

    public List<String> getExcludeUri() {
        return excludeUri;
    }

    public void setExcludeUri(List<String> excludeUri) {
        this.excludeUri = excludeUri;
    }
}
