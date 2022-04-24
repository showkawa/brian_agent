package com.brian.brianagent.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MaskHelperFilter {

    public static Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        System.out.println("=== <agent method>request ===:" + request.getURI());
        return chain.filter(exchange.mutate().request(request).build());
    }


//    @RuntimeType
//    public static Object intercept(@Origin Method method,
//                                   @SuperCall Callable<?> callable) {
//        long start = System.currentTimeMillis();
//        try {
//            System.out.println("method >>>>>:" + method);
//            return callable.call();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println(method + " took " + (System.currentTimeMillis() - start));
//        }
//    }

}
