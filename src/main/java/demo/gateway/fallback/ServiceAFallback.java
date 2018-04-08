package demo.gateway.fallback;

import org.springframework.stereotype.Component;

/**
 * @author: liuzhiyu <279044328@qq.com>
 * @date: 05/04/2018
 */
@Component
public class ServiceAFallback extends BaseFallback {
    public ServiceAFallback() {
        super("service-a is unavailable. Please try it again later.");
    }

    @Override
    public String getRoute() {
        return "service-a";
    }
}
