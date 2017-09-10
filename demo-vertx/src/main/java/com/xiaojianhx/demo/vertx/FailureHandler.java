package com.xiaojianhx.demo.vertx;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class FailureHandler implements Handler<RoutingContext> {

    private Logger log = LogManager.getLogger(getClass());

    public void handle(RoutingContext context) {

        log.debug(getClass().getSimpleName() + "-" + Thread.currentThread());
        log.error(ExceptionUtils.getFullStackTrace(context.failure()));

        context.response().end("FailureHandler end");
    }
}
