package com.xiaojianhx.demo.redis;

import redis.clients.jedis.Jedis;

public class SingleRedisClientTest {

    public static void main(String[] args) {

        SingleRedisClient client = new SingleRedisClient();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                // String key = "key-" + Thread.currentThread().getName();
                // String value = "value-" + Thread.currentThread().getName();
                // System.out.println(client.jedisPool.getResource().set(key,
                // value));

                Jedis jedis = client.getConnection();

                String str = jedis.get("name");
                System.out.println(Thread.currentThread().getName() + "-->" + jedis.get("name"));

                if (str == null) {
                    jedis.set("name", "gao");
                }

                client.close();
            }).start();
        }
    }
}