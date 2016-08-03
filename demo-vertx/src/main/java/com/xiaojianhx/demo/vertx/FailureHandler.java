package com.xiaojianhx.demo.vertx;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class FailureHandler implements Handler<RoutingContext> {

    private Logger log = LoggerFactory.getLogger(FailureHandler.class);

    public void handle(RoutingContext context) {

        log.debug(getClass().getSimpleName() + "-" + Thread.currentThread());
        log.error(ExceptionUtils.getFullStackTrace(context.failure()));

        context.response().end("FailureHandler end");
    }
}
