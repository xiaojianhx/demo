package com.xiaojianhx.demo.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Server implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String msg;

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObserver() {
        observers.forEach(o -> o.update(msg));
    }

    public void update(String msg) {
        this.msg = msg;
        System.out.println("更新消息： " + msg);
        notifyObserver();
    }
}