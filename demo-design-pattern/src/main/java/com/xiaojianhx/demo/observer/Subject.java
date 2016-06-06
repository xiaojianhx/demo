package com.xiaojianhx.demo.observer;

public interface Subject {

    void add(Observer o);

    void del(Observer o);

    void notifyObservers();

    void operation();
}