package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class Router2Handler implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        System.out.println(getClass().getSimpleName() + "-" + Thread.currentThread());
        System.out.println(1 / 0);
        context.next();
    }
}