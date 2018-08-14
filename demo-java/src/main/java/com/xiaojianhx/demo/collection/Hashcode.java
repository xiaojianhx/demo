package com.xiaojianhx.demo.collection;

import java.util.ArrayList;
import java.util.List;

public class Hashcode {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        System.out.println(list.hashCode());

        list.add("1");
        System.out.println(list.hashCode());
    }
}