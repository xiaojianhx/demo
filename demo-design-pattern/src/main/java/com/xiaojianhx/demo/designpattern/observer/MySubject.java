package com.xiaojianhx.demo.designpattern.observer;

public class MySubject extends AbstractSubject {

    public void operation() {

        System.out.println("update self!");
        notifyObservers();
    }
}