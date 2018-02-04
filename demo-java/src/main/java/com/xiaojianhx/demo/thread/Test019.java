package com.xiaojianhx.demo.thread;

/**
 * 生产者消费者
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年2月4日下午10:15:54
 */
public class Test019 {

    public static void main(String[] args) {
        String lock = "";

        P p = new P(lock);
        C c = new C(lock);

        new Thread(() -> {
            while (true) {
                p.setValue();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                c.getValue();
            }
        }).start();
    }

    static class P {

        private String lock;

        public P(String lock) {
            this.lock = lock;
        }

        public void setValue() {

            try {
                synchronized (lock) {
                    if (!ValueObject.value.equals("")) {
                        lock.wait();
                    }
                    String value = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println("set:" + value);
                    ValueObject.value = value;
                    lock.notify();
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

                    if (ValueObject.value.equals("")) {
                        lock.wait();
                    }
                    System.out.println("get:" + ValueObject.value);
                    ValueObject.value = "";
                    lock.notify();
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