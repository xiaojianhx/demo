package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class FailureHandler implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        System.out.println(getClass().getSimpleName() + "-" + Thread.currentThread());

        StackTraceElement[] t = context.failure().getStackTrace();
        for (StackTraceElement stackTraceElement : t) {
            System.out.println(stackTraceElement);
        }
        context.response().end("FailureHandler end");
    }
}
