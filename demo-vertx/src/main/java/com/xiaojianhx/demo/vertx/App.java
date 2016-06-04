package com.xiaojianhx.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class App extends AbstractVerticle {

    public void start() throws Exception {

        super.start();

        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Thread.currentThread());

        final HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/path/*").order(2).handler(new Router1Handler());
        router.route("/path/*").order(1).handler(new Router2Handler());

        Router subRouter = Router.router(vertx);
        subRouter.route("/sub").handler(new SubRouter1Handler());
        subRouter.route("/sub").handler(new SubRouter2Handler());
        subRouter.route("/sub").handler(new SubRouter3Handler());
        router.mountSubRouter("/path", subRouter);

        router.route("/path/*").blockingHandler(new Router3Handler());
        router.route("/path/*").last().handler(new EndHandler());

        server.requestHandler(router::accept).listen(8080, "localhost");
    }
}
