package com.xiaojianhx.demo;

public interface Handler<T> {

    T handle(T a, T b);
}