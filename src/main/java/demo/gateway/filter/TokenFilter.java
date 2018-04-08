package demo.gateway.filter;

import demo.gateway.helper.ResponseHelper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuzhiyu < 279044328@qq.com >
 * @date 2018-04-05
 */


public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 在请求被路由之前调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        // FILTER执行顺序，通过数字指定，数字越大，越靠后执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 是否执行该过滤器，true为执行，false为不执行
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURL().toString();
        logger.debug("=== TokenFilter: ", request.getMethod(), url);

        String token = "";
        try {
            token = request.getHeader("Authorization");
        } catch (Exception e) {
            ResponseHelper.setErrorResponse(ctx, HttpStatus.UNAUTHORIZED.value(), "Get authorization failed: " + e.getMessage());
            return null;
        }

        if (StringUtils.isEmpty(token)) {
            ResponseHelper.setErrorResponse(ctx, HttpStatus.UNAUTHORIZED.value(), "Token is empty.");
            return null;
        }

        // TODO 验证token的合法性

        return null;
    }

    private Logger logger = LoggerFactory.getLogger(TokenFilter.class);
}
