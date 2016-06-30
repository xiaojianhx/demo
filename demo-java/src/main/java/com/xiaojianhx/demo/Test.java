package com.xiaojianhx.demo;

public class Test {

    public static void main(String[] args) {

        // for (int i : init()) {
        // System.out.println(i);
        // }
        for (int i = 0; i < init().length; i++) {
            System.out.println(i);
        }
    }

    private static int[] init() {
        System.out.println("a");
        return new int[] { 1, 2, 3, 4, 5 };
    }
}
