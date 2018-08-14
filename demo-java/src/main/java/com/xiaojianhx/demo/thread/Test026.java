package com.xiaojianhx.demo.thread;

/**
 * volatile
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年8月14日下午9:54:16
 */
public class Test026 {

    private boolean flg = true;

    private void test() {

        while (flg) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
    }

    public static void main(String[] args) {

        Test026 test = new Test026();

        Thread t0 = new Thread(() -> {
            test.test();
        });

        Thread t1 = new Thread(() -> {
            test.setFlg(false);
        });

        t0.start();
        ThreadUtils.sleep(1000);
        t1.start();
        try {
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}
