package com.xiaojianhx.demo.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedRedisClient {

    private ShardedJedisPool pool;

    private ThreadLocal<ShardedJedis> local;

    public ShardedRedisClient() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(20);
        config.setMinIdle(5);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

        shards.add(new JedisShardInfo("host", 6379));
        shards.add(new JedisShardInfo("host", 6379));
        pool = new ShardedJedisPool(config, shards);

        local = new ThreadLocal<ShardedJedis>();
    }

    public ShardedJedis getConnection() {

        ShardedJedis jedis = local.get();

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