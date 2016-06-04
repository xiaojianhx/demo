package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class EndHandler implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        System.out.println(getClass().getSimpleName() + "-" + Thread.currentThread());
        context.response().end("end");
    }
}