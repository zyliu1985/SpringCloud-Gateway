package demo.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * @author: liuzhiyu <279044328@qq.com>
 * @date: 05/04/2018
 */
@Component
public class PostErrorFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        // 必须大于ErrorFilter的Order
        return 30;
    }

    @Override
    public boolean shouldFilter() {
        // 判断： 仅处理来自post过滤器引起的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter postFailedFilter = (ZuulFilter) ctx.get("failed.filter");
        if (postFailedFilter != null && postFailedFilter.filterType().equals("post")) {
            return true;
        }
        return false;
    }
}
