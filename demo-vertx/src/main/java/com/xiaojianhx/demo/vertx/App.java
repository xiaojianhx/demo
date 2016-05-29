package com.xiaojianhx.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;

public class App extends AbstractVerticle {

    public void start() throws Exception {

        super.start();

         VertxOptions options = null;
         final HttpServer server = Vertx.vertx(options).createHttpServer();
//        final HttpServer server = Vertx.vertx().createHttpServer();

        server.requestHandler(handler -> {
            handler.response().end("hello");
        });

        server.listen(8080, "localhost");
    }
}
