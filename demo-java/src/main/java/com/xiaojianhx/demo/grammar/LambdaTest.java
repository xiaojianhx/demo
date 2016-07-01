package com.xiaojianhx.demo.grammar;

public class LambdaTest {

    public static void main(String[] args) {

        System.out.println(handle(2, 1, (a, b) -> a + b));
        System.out.println(handle(2, 1, (a, b) -> a - b));
        System.out.println(handle(2, 1, (a, b) -> a * b));
        System.out.println(handle(2, 1, (a, b) -> a / b));
    }

    private static <T extends Number> T handle(T a, T b, Handler<T> handler) {
        return handler.handle(a, b);
    }
}