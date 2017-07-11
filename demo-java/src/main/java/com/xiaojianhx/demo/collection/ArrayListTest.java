package com.xiaojianhx.demo.collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

    private static int REPEAT_COUNT = 10000;
    private static int ELEMENT_SIZE = 1000000;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        test1();
        System.out.println("不设容量，用时" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        test2();
        System.out.println("设定容量，用时" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void test1() {

        for (int i = 0; i < REPEAT_COUNT; i++) {

            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < ELEMENT_SIZE; j++) {
                data.add(j);
            }
        }
    }

    private static void test2() {

        for (int i = 0; i < REPEAT_COUNT; i++) {

            List<Integer> data = new ArrayList<>(ELEMENT_SIZE);
            for (int j = 0; j < ELEMENT_SIZE; j++) {
                data.add(j);
            }
        }
    }
}
