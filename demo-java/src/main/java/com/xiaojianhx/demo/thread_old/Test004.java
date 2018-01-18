package com.xiaojianhx.demo.thread_old;

/**
 * 脏读，改进的方法 @see {@link Test005}
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月4日下午9:52:25
 */
public class Test004 {

    public static void main(String[] args) {

        Service service = new Service();
        new Thread(new ThreadA(service)).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.get();
    }

    private static class ThreadA implements Runnable {

        private Service service;

        public ThreadA(Service service) {
            this.service = service;
        }

        public void run() {
            service.set("B", "BB");
        }
    }

    private static class Service {

        private String username = "A";
        private String password = "AA";

        synchronized public void set(String username, String password) {

            try {
                this.username = username;
                Thread.sleep(5000);
                this.password = password;
                System.out.printf("set username = %s, password=%s\n", username, password);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void get() {
            System.out.printf("get username = %s, password=%s\n", username, password);
        }
    }
}
