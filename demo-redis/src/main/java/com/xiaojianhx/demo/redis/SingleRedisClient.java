package com.xiaojianhx.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SingleRedisClient {

    private JedisPool pool;

    private ThreadLocal<Jedis> local;

    public SingleRedisClient() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMinIdle(5);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        pool = new JedisPool(config, "host", 6379);

        local = new ThreadLocal<Jedis>();
    }

    public Jedis getConnection() {

        Jedis jedis = local.get();

        if (jedis == null) {
            jedis = pool.getResource();
            local.set(jedis);
        }

        return jedis;
    }

    public void close() {
        local.get().close();
        local.remove();
    }
}