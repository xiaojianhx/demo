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

        String hostA = "192.168.1.120";
        int portA = 6379;
        String hostB = "192.168.1.80";
        int portB = 6379;

        List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>(2);
        JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
        JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
        jdsInfoList.add(infoA);
        jdsInfoList.add(infoB);

        pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {

            final int adx = i;

            Thread thread = new Thread(() -> {
                String key = generateKey(adx);
                ShardedJedis jds = null;

                try {
                    jds = pool.getResource();
                } catch (Exception e) {
                    e.printStackTrace();

                    if (jds != null) {
                        jds.close();
                    }
                }

                try {
                    System.out.println(key + ":" + jds.getShard(key).getClient().getHost());
                    System.out.println(jds.set(key, adx + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    jds.close();
                }
            });

            thread.start();
        }

        for (int i = 0; i < 100; i++) {

            String key = generateKey(i);
            ShardedJedis jds = null;

            try {
                jds = pool.getResource();
            } catch (Exception e) {
                e.printStackTrace();
                jds.close();
            }

            try {
                System.out.println(jds.get(key) + ":" + jds.getShard(key).getClient().getHost());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jds.close();
            }
        }
    }

    public static String generateKey(int idx) {
        return idx + "";
    }
}