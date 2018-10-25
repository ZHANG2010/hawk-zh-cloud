package com.hawk.base.zuul.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * MyFilter
 *
 * @author hawk_zhang
 * @date 2018/10/25
 */
@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 过滤的类型
     * pre:路由之前
     * routing:路由之时
     * post:路由之后
     * error:发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * 数字越小，优先级越高
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 可以写逻辑判断，是否过滤
     * true：永远过滤
     * false:不过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * 可以很复杂，包括查sql,nosql去判断该请求到底有没有权限访问
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s MyFilter request to %s",request.getMethod(),request.getRequestURL().toString()));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username!= null && password != null){
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);
            return null;
        }else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.set("isSuccess",false);
            ctx.setResponseBody("{\"result\":\"用户名密码错误！\"}");
        }
        return null;
    }
}
