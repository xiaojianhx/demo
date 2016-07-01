package com.xiaojianhx.demo.grammar;

public interface Handler<T> {

    T handle(T a, T b);
}