package demo.gateway.helper;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Request;

/**
 * @author: liuzhiyu <279044328@qq.com>
 * @date: 05/04/2018
 */
public class ResponseHelper {

    public static void setErrorResponse(RequestContext ctx, int errorCode, String message) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(errorCode);
        ctx.set("error.status_code", errorCode);
        ctx.set("error.message", message);
        ctx.set("success", false);
        logger.error(message);
    }

    private static Logger logger = LoggerFactory.getLogger(ResponseHelper.class);
}
