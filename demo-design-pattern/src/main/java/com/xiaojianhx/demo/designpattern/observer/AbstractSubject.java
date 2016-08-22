package com.xiaojianhx.demo.designpattern.observer;

import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();

    public void add(Observer o) {
        vector.add(o);
    }

    public void del(Observer o) {
        vector.remove(o);
    }

    public void notifyObservers() {

        Enumeration<Observer> enumo = vector.elements();
        while (enumo.hasMoreElements()) {
            enumo.nextElement().update();
        }
    }
}