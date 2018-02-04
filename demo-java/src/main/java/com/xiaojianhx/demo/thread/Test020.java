package com.xiaojianhx.demo.thread;

/**
 * 生产者消费者
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日下午10:15:54
 */
public class Test020 {

    public static void main(String[] args) {
        String lock = "";

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                P p = new P(lock);
                while (true) {
                    p.setValue();
                }
            }).start();

            new Thread(() -> {
                C c = new C(lock);
                while (true) {
                    c.getValue();
                }
            }).start();
        }
    }

    static class P {

        private String lock;

        public P(String lock) {
            this.lock = lock;
        }

        public void setValue() {

            try {
                synchronized (lock) {
                    while (ValueObject.value.equals("")) {

                        System.out.println("消费者" + Thread.currentThread().getName() + " WAITING...");
                        lock.wait();
                    }
                    System.out.println("消费者" + Thread.currentThread().getName() + " WAITING...");

                    String value = System.currentTimeMillis() + "_" + System.nanoTime();
                    ValueObject.value = value;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class C {

        private String lock;

        public C(String lock) {
            this.lock = lock;
        }

        public void getValue() {

            try {
                synchronized (lock) {

                    while (ValueObject.value.equals("")) {

                        System.out.println("消费者" + Thread.currentThread().getName() + " WAITING...");
                        lock.wait();
                    }

                    System.out.println("消费者" + Thread.currentThread().getName() + " RUNNABLE...");
                    ValueObject.value = "";
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ValueObject {

        public static String value = "";
    }
}