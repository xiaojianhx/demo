package com.xiaojianhx.demo.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class HyploglogTest {

    @Test
    public void testConnection() {

        var count = 10000000;

        var key = "xx";
        Jedis jedis = new Jedis("192.168.1.80", 6379);
        jedis.select(0);
        jedis.del(key);

        var mode = 1000000;
        var p = jedis.pipelined();

        var arr = new String[mode];
        var idx = 0;

        for (int i = 0; i < count; i++) {

            if (i != 0 && i % mode == 0) {
//                System.out.println(Arrays.deepToString(arr));
                p.pfadd(key, arr);
                idx = 0;
                arr[idx++] = String.valueOf(i);
                continue;
            }

            arr[idx++] = String.valueOf(i);
        }
//        System.out.println(Arrays.deepToString(arr));
        p.pfadd(key, arr);
        p.sync();

        var pfcount = jedis.pfcount(key);
        System.out.println(jedis.pfcount(key) + "误差：" + (Math.abs(count - pfcount) * 1000.0 / count) + "‰");
        // 9973402误差：2.6598‰

        jedis.close();
    }
}
