package com.xiaojianhx.demo.jvm;

/**
 * -Xss128k
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月16日下午8:45:32
 */
public class ErrorTest {

    private int stack = 1;

    public void stackLeak() {
        stack++;
        stackLeak();
    }

    public static void main(String[] args) {

        ErrorTest test = new ErrorTest();
        try {
            test.stackLeak();
        } catch (Exception e) {
            System.out.println("stack = " + test.stack);
            e.printStackTrace();
        }
    }
}