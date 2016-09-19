package com.xiaojianhx.demo.redis;

public class ShardedRedisClientTest {

    public static void main(String[] args) {

        ShardedRedisClient client = new ShardedRedisClient();

        long start = System.currentTimeMillis();
        int count = 100;
        for (int i = 0; i < count; i++) {
            String key = "key-" + i;
            String value = "value-" + i;
            // System.out.println(client.getConnection().set(key, value));
            client.getConnection().set(key, value);
        }

        System.out.println("入库" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            // System.out.println(client.getConnection().get("key-8"));
            client.getConnection().get("key-" + i);
        }
        System.out.println("出库" + (System.currentTimeMillis() - start));
        // System.out.println(client.getConnection().del("key-8"));
        client.close();
    }
}