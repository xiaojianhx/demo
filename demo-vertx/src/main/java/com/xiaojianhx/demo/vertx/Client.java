package com.xiaojianhx.demo.vertx;

import io.vertx.core.AbstractVerticle;

public class Client extends AbstractVerticle {

    public void start() throws Exception {

        vertx.createHttpClient().getNow(80, "www.baidu.com", "/", resp -> {
            System.out.println("Got response " + resp.statusCode());
            resp.bodyHandler(body -> {
                System.out.println("Got data " + body.toString("utf-8"));
            });
        });
    }
}
