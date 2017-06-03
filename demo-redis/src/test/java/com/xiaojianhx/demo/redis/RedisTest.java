package com.xiaojianhx.demo.redis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

    @Test
    public void testConnection() {

        try {
            Jedis jedis = new Jedis("192.168.1.220", 6379);
            jedis.set("name", "gao");

            // 有效期
            jedis.setex("name", 10, "setex");

            String name = jedis.get("name");
            System.out.println(name);

            jedis.append("name", "append");
            System.out.println(jedis.get("name"));

            // list
            jedis.lpush("list", "c", "d");
            List<String> list = jedis.lrange("list", 0, 100);
            System.out.println(list);

            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
