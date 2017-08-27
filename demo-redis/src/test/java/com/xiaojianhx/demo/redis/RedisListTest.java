package com.xiaojianhx.demo.redis;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisListTest {

    @Test
    public void testPubSub() {

        try {

            new Thread(() -> {
                Jedis jedis = getJedis();
                for (int i = 0; i < 20; i++) {
                    jedis.lpush("task-queue", "message-" + i);
                }
                jedis.close();
            }).start();

            for (int i = 0; i < 1; i++) {

                Jedis jedis = getJedis();
                new Thread(() -> {
                    while (true) {
                        System.out.println(jedis.rpop("task-queue") == null);
                        System.out.println(Thread.currentThread().getName() + "-" + jedis.rpop("task-queue"));
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    private Jedis getJedis() {
        Jedis jedis = new Jedis("192.168.1.151", 6379);
        jedis.select(0);
        return jedis;
    }

    public static void main(String[] args) {
        new RedisListTest().testPubSub();
    }
}
