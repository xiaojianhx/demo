package com.xiaojianhx.demo.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisInitTest {

    private JedisPool pool;

    private String KEY = "list";

    @Before
    public void setUp() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(200);
        config.setMinIdle(200);

        pool = new JedisPool(config, "192.168.1.151", 6379);
    }

    @Test
    public void testPush() {

        Jedis jedis = pool.getResource();

        jedis.del(KEY);
        for (int i = 0; i < 1000000; i++) {
            jedis.lpush("list", "message-" + i);
        }
        jedis.close();
    }
}
