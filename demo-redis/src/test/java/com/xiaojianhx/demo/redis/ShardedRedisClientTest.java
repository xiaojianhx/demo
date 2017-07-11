package com.xiaojianhx.demo.redis;

import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

public class ShardedRedisClientTest {

    static ShardedJedisPool pool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(500);
        config.setMaxWaitMillis(1000 * 1000);
        config.setTestOnBorrow(true);

        String hostA = "192.168.1.111";
        int portA = 6379;

        String hostB = "192.168.1.112";
        int portB = 6379;

        String hostC = "192.168.1.113";
        int portC = 6379;

        List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);

        JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
        JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
        JedisShardInfo infoC = new JedisShardInfo(hostC, portC);

        jdsInfoList.add(infoA);
        jdsInfoList.add(infoB);
        jdsInfoList.add(infoC);

        pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {

            final int idx = i;
            new Thread(() -> {

                ShardedJedis jds = pool.getResource();
                String key = generateKey(idx);
                System.out.println(key + ":" + "\t" + jds.set(key, key) + "\t" + jds.getShard(key).getClient().getHost());
                jds.close();
            }).start();
        }
    }

    public static String generateKey(int idx) {
        return idx + "";
    }
}