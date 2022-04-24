package com.brian.brianagent.service;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class BrianCustAdvice {

//    @Advice.OnMethodEnter
//    public static void intercept(@Advice.Return(readOnly = false) String value) {
//        System.out.println("intercepted: " + value);
//        value =  "        putHeaderToLog(exchange, \"x-request-region\");\n" +
//                "        putHeaderToLog(exchange, \"x-request-id\");\n" +
//                "        ServerHttpRequest request = exchange.getRequest();\n" +
//                "        log.info(\"=== request55555555555555 ===:{}\", request.getURI());\n" +
//                "        return Mono.empty();";
//    }

    @RuntimeType()
    public static Object intercept(
            @Origin Method method,
            @SuperCall Callable<?> callable
    ) {
        System.out.println("method name: " + method.getName());
        long start = System.currentTimeMillis();
        try {
            try {
                return callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println(
                    "Took " + (System.currentTimeMillis() - start));
        }
        return 0;
    }
}
