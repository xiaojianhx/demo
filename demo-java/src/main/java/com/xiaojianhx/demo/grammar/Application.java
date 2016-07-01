package com.xiaojianhx.demo.grammar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<Integer> data = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8, 0);

        Collections.sort(data, (a, b) -> b - a);

        Collections.sort(data, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
    }
}