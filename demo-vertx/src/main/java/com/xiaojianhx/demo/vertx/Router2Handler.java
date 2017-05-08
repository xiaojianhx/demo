package com.xiaojianhx.demo.vertx;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.web.RoutingContext;

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

        // AsyncSQLClient mySQLClient = MySQLClient.createShared(rc.vertx(),
        // mySQLClientConfig);

        // // Connect to the database
        // mySQLClient.rxGetConnection().subscribe(conn -> {
        //
        // Single<ResultSet> resa = conn.rxQuery("select * from goods where id =
        // 14910").flatMap(
        // r -> conn.rxQueryWithParams("select * from stores where id = ?", new
        // JsonArray().add(r.getRows().get(0).getLong("store_id"))))
        // .flatMap(r -> conn.rxQueryWithParams("select * from sellers where id
        // = ?",
        // new JsonArray().add(r.getRows().get(0).getLong("seller_id"))))
        // .flatMap(r -> conn.rxQueryWithParams("select * from users where id =
        // ?",
        // new JsonArray().add(r.getRows().get(0).getLong("user_id"))));
        //
        // System.out.println(resa);
        // }, err -> {
        // err.printStackTrace();
        // });
        // Connect to the database
        rc.next();
    }
}