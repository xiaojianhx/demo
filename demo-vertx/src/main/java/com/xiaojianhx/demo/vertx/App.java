package com.xiaojianhx.demo.vertx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;

/**
 * 
 * start like 'java -Djava.ext.dirs=./lib;.; io.vertx.core.Launcher run
 * com.xiaojianhx.demo.vertx.App -conf config.json'
 */
public class App extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(App.class);

    public void start() throws Exception {

        super.start();

        log.debug(Runtime.getRuntime().availableProcessors() + "");
        log.debug(Thread.currentThread() + "");

        final HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

//        router.route("/path/*").order(2).handler(new Router1Handler());
        router.route("/path/*").order(1).handler(new Router2Handler());
//
//        Router subRouter = Router.router(vertx);
//        subRouter.route("/sub").handler(new SubRouter1Handler());
//        subRouter.route("/sub").handler(new SubRouter2Handler());
//        subRouter.route("/sub").handler(new SubRouter3Handler());
//        router.mountSubRouter("/path", subRouter);
//
//        router.route("/path/*").blockingHandler(new Router3Handler());
//        router.route("/path/*").last().handler(new EndHandler());
//        router.route("/path/*").failureHandler(new FailureHandler());

        server.requestHandler(router::accept).listen(config().getInteger("service.port"), config().getString("service.host"));
    }
}
