package demo.gateway.filter;

import demo.gateway.helper.ResponseHelper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author: liuzhiyu <279044328@qq.com>
 * @date: 05/04/2018
 */
public class ErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable t = ctx.getThrowable();
        ctx.set("error.exception", t.getCause());
        ResponseHelper.setErrorResponse(ctx, 500, t.getMessage());
        return null;
    }
}
