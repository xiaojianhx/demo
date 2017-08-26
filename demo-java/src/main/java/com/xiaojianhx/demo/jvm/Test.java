package com.xiaojianhx.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutFoMemoryError
 * 
 * @author xiaojianhx
 * @version V1.0.0 $ 2017年7月16日下午8:45:32
 */
public class Test {

    public static void main(String[] args) {

        List<Class> data = new ArrayList<>();

        while (true) {
            data.add(new Class());
        }
    }

    private static class Class {
    }
}