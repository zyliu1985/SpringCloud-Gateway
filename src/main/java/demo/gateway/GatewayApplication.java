package demo.gateway;

import demo.gateway.filter.ErrorFilter;
import demo.gateway.filter.PostErrorFilter;
import demo.gateway.filter.TokenFilter;
import demo.gateway.processor.CustomerFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by 刘志宇 on 2017/11/15.
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrix
public class GatewayApplication {
    public static void main(String[] args) {
        FilterProcessor.setProcessor(new CustomerFilterProcessor());
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public PostErrorFilter postErrorFilter() {
        return new PostErrorFilter();
    }
}
