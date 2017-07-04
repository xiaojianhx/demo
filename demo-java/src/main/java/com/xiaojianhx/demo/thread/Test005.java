package com.xiaojianhx.demo.thread;

public class Test005 {

    public static void main(String[] args) {

        Service service = new Service();
        new Thread(() -> service.set("B", "BB")).start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.get();
    }

    private static class Service {

        private String username = "A";
        private String password = "AA";

        synchronized public void set(String username, String password) {

            try {
                this.username = username;
                Thread.sleep(1000);
                this.password = password;
                System.out.printf("set username = %s, password=%s\n", username, password);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void get() {
            System.out.printf("get username = %s, password=%s\n", username, password);
        }
    }
}
