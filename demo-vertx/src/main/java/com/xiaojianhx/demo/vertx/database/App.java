package com.xiaojianhx.demo.vertx.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xiaojianhx.demo.vertx.EndHandler;
import com.xiaojianhx.demo.vertx.FailureHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class App extends AbstractVerticle {

    private Logger log = LogManager.getLogger(getClass());

    public void start() throws Exception {

        log.debug("service starting...");

        super.start();

        final HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/database/*").handler(new ServiceHandle(vertx, config()));
        router.route("/database/*").last().handler(new EndHandler());
        router.route("/database/*").failureHandler(new FailureHandler());

        server.requestHandler(router::accept).listen(config().getInteger("service.port"), config().getString("service.host"));

        log.debug("service started");
    }
}