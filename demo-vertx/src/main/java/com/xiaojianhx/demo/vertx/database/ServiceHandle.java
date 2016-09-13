package com.xiaojianhx.demo.vertx.database;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.web.RoutingContext;

public class ServiceHandle implements Handler<RoutingContext> {

    private Logger log = LoggerFactory.getLogger(getClass());

    private AsyncSQLClient client;

    public ServiceHandle(Vertx vertx, JsonObject config) {
        this.client = MySQLClient.createShared(vertx, config);
    }

    public void handle(RoutingContext event) {

        client.getConnection(conn -> {

            CompletableFuture<Integer> future = new CompletableFuture<Integer>();

            if (conn.failed()) {
                System.err.println(conn.cause().getMessage());
                return;
            }

            future.complete(1);

            future.thenComposeAsync(num -> {

                log.info(Thread.currentThread().getName());
                CompletableFuture<Integer> tmp = new CompletableFuture<Integer>();

                conn.result().setAutoCommit(false, res -> {
                    if (res.failed()) {
                        tmp.completeExceptionally(res.cause());
                    }
                    tmp.complete(num + 1);
                });
                return tmp;
            }).thenComposeAsync(num -> {

                log.info(Thread.currentThread().getName());
                CompletableFuture<Integer> tmp = new CompletableFuture<Integer>();

                conn.result().execute("insert into a(name) values('Hello a')", res -> {
                    if (res.failed()) {
                        tmp.completeExceptionally(res.cause());
                    }
                    tmp.complete(num + 1);
                });
                return tmp;
            }).thenComposeAsync(num -> {

                log.info(Thread.currentThread().getName());

                CompletableFuture<Integer> tmp = new CompletableFuture<Integer>();

                conn.result().execute("insert into b1(name) values('Hello b')", res -> {
                    if (res.failed()) {
                        tmp.completeExceptionally(res.cause());
                    }
                    tmp.complete(num + 1);
                });

                return tmp;
            }).whenCompleteAsync((num, ex) -> {
                log.info(Thread.currentThread().getName() + "," + num);

                if (ex != null) {
                    log.error(ExceptionUtils.getFullStackTrace(ex));
                    conn.result().rollback(handler -> {
                        if (handler.failed()) {
                            log.error(ExceptionUtils.getFullStackTrace(handler.cause()));
                        }
                        log.info("rollback");
                    });
                } else {
                    conn.result().commit(handler -> {
                        if (handler.failed()) {
                            log.error(ExceptionUtils.getFullStackTrace(handler.cause()));
                        }
                        log.info("commit");
                    });
                }

                conn.result().close();
            });
        });

        event.next();
    }
}