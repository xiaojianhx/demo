package com.xiaojianhx.demo.thread;

/**
 * 死循环
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2018年1月20日下午2:51:25
 */
public class Test009 {

    public static void main(String[] args) {

        Service009 service = new Service009();
        new Thread(service).start();
        ThreadUtils.sleep(100);
        System.out.println("要中断他");
        service.setFlg(false);
    }
}

class Service009 implements Runnable {

    private boolean flg = true;

    public void run() {

        while (flg) {
            System.out.println(Thread.currentThread().getName() + " run");
        }
        System.out.println("被中断了");
    }

    public boolean isFlg() {
        return flg;
    }

    public void setFlg(boolean flg) {
        this.flg = flg;
    }
}