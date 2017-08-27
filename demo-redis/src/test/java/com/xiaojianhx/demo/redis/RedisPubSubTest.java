package com.xiaojianhx.demo.redis;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisPubSubTest {

    @Test
    public void testPubSub() {

        try {

            new Thread(() -> {
                Jedis jedis = getJedis();
                for (int i = 0; i < 2000000; i++) {
                    jedis.publish("channel", "message-" + i);
                }
                jedis.close();
            }).start();

            Jedis jedis = getJedis();
            new Thread(() -> {
                while (true) {
                    jedis.subscribe(new RedisMsgPubSubListener(), "channel");
                }
            }).start();
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

    private static class RedisMsgPubSubListener extends JedisPubSub {

        public void onMessage(String channel, String message) {
            System.out.println(Thread.currentThread().getName() + "-channel:" + channel + " receives message :" + message);
        }

        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.println("channel:" + channel + " is been subscribed:" + subscribedChannels);
        }

        public void onUnsubscribe(String channel, int subscribedChannels) {
            System.out.println("channel:" + channel + " is been unsubscribed:" + subscribedChannels);
        }
    }

    public static void main(String[] args) {
        new RedisPubSubTest().testPubSub();
    }
}
