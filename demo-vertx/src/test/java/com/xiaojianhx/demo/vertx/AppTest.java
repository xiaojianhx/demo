package com.xiaojianhx.demo.vertx;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class AppTest {

    @Test
    public void test0() {

        int count = 2;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(new ThreadTest(latch)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ThreadTest implements Runnable {

        private CountDownLatch latch;

        public ThreadTest(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            get();
            latch.countDown();
        }

        private void get() {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpGet get = new HttpGet("http://localhost:8080/path/sub");
                CloseableHttpResponse httpResponse = null;
                httpResponse = httpClient.execute(get);
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("响应状态码:" + httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}