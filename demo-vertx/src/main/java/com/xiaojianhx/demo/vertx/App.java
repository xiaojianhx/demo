package com.xiaojianhx.demo.vertx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * 
 * start like 'java -Djava.ext.dirs=./lib;.; io.vertx.core.Launcher run
 * com.xiaojianhx.demo.vertx.App -conf config.json'
 */
public class App extends AbstractVerticle {

    private Logger log = LogManager.getLogger(getClass());

    public void start() throws Exception {

        super.start();

        log.debug(Runtime.getRuntime().availableProcessors() + "");
        log.debug(Thread.currentThread() + "");

        final HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        // router.route().handler(rc -> {
        // HttpServerResponse response = rc.response();
        // response.putHeader("content-type", "text/plain");
        // response.end("ok");
        // });

        router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.setChunked(true);

            response.write("route1\n");
            routingContext.vertx().setTimer(1000, tid -> routingContext.next());
        });

        router.route("/some/path/").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route2\n");
            routingContext.vertx().setTimer(1000, tid -> routingContext.next());
        });

        router.route("/some/path/").consumes("application/json").handler(routingContext -> {

            HttpServerResponse response = routingContext.response();
            response.write("route3").end();
        });

        // router.route("/path/*").order(2).handler(new Router1Handler());
        // router.route("/path/*").order(1).handler(new Router2Handler());
        //
        // Router subRouter = Router.router(vertx);
        // subRouter.route("/sub").handler(new SubRouter1Handler());
        // subRouter.route("/sub").handler(new SubRouter2Handler());
        // subRouter.route("/sub").handler(new SubRouter3Handler());
        // router.mountSubRouter("/path", subRouter);
        //
        // router.route("/path/*").blockingHandler(new Router3Handler());
        // router.route("/path/*").last().handler(new EndHandler());
        // router.route("/path/*").failureHandler(new FailureHandler());

        server.requestHandler(router::accept).listen(config().getInteger("service.port"), config().getString("service.host"));
    }
}
