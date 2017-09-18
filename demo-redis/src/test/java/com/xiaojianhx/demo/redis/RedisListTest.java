package com.xiaojianhx.demo.redis;

import java.util.concurrent.atomic.AtomicInteger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisListTest {

    private static JedisPool pool;

    private static String KEY = "list";

    private static AtomicInteger counter = new AtomicInteger(0);

    public static void init() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(200);
        config.setMinIdle(200);

        pool = new JedisPool(config, "192.168.1.151", 6379);
    }

    public static void main(String[] args) {

        init();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    Jedis jedis = pool.getResource();
                    String val = jedis.rpop(KEY);

                    if (val != null) {
                        counter.incrementAndGet();
                    }
                    jedis.close();
                }
            }).start();
        }

        new Thread(() -> {
            while (true) {
                System.out.println(counter.get());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
