package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.rxjava.ext.web.RoutingContext;

public class Router1Handler implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        System.out.println(getClass().getSimpleName() + "-" + Thread.currentThread());
        context.next();
    }
}
