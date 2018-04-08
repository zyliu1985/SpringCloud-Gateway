package demo.gateway.fallback;

import org.springframework.stereotype.Component;

/**
 * @author: liuzhiyu <279044328@qq.com>
 * @date: 06/04/2018
 */
@Component
public class ServiceBFallback extends BaseFallback {
    public ServiceBFallback() {
        super("service-b is unavailable. Please try it again later.");
    }

    @Override
    public String getRoute() {
        return "service-b";
    }
}
