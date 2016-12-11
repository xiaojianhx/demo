package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.rxjava.ext.asyncsql.AsyncSQLClient;
import io.vertx.rxjava.ext.asyncsql.MySQLClient;
import io.vertx.rxjava.ext.web.RoutingContext;
import rx.Observable;

public class Router2Handler implements Handler<RoutingContext> {

    public void handle(RoutingContext rc) {

        JsonObject mySQLClientConfig = new JsonObject();
        mySQLClientConfig.put("host", "172.16.1.5");
        mySQLClientConfig.put("port", 3306);
        mySQLClientConfig.put("maxPoolSize", 20);
        mySQLClientConfig.put("username", "root");
        mySQLClientConfig.put("password", "123.com");
        mySQLClientConfig.put("database", "jmuv3");
        mySQLClientConfig.put("charset", "utf8");

        AsyncSQLClient mySQLClient = MySQLClient.createShared(rc.vertx(), mySQLClientConfig);

        // Connect to the database
        mySQLClient.getConnectionObservable().subscribe(conn -> {

            Observable<ResultSet> resa = conn.queryObservable("select * from goods where id = 14910")
                    .flatMap(r -> conn.queryWithParamsObservable("select * from stores where id = ?",
                            new JsonArray().add(r.getRows().get(0).getLong("store_id"))))
                    .flatMap(r -> conn.queryWithParamsObservable("select * from sellers where id = ?",
                            new JsonArray().add(r.getRows().get(0).getLong("seller_id"))))
                    .flatMap(r -> conn.queryWithParamsObservable("select * from users where id = ?",
                            new JsonArray().add(r.getRows().get(0).getLong("user_id"))));

            resa.subscribe(resultSet -> {
                System.out.println("Results : " + resultSet.getRows());
            }, err -> {
                System.out.println("Database problem");
                err.printStackTrace();
            }, conn::close);
        }, err -> {
            err.printStackTrace();
        });

        // test1(mySQLClient);
        rc.next();
    }

    // private void test1(AsyncSQLClient mySQLClient) {
    // mySQLClient.getConnection(r -> {
    //
    // if (r.failed()) {
    // throw new RuntimeException(r.cause());
    // }
    //
    // SQLConnection conn = r.result();
    //
    // conn.query("select * from goods where id = 14910", r1 -> {
    //
    // long store_id = r1.result().getRows().get(0).getLong("store_id");
    //
    // conn.queryWithParams("select * from stores where id = ?", new
    // JsonArray().add(store_id), r2 -> {
    //
    // long seller_id = r2.result().getRows().get(0).getLong("seller_id");
    //
    // conn.queryWithParams("select * from sellers where id = ?", new
    // JsonArray().add(seller_id), r3 -> {
    //
    // long user_id = r3.result().getRows().get(0).getLong("user_id");
    // conn.queryWithParams("select * from users where id = ?", new
    // JsonArray().add(user_id), r4 -> {
    // System.out.println(r4.result().getRows().get(0));
    // });
    // });
    // });
    // });
    // });
    // }
}