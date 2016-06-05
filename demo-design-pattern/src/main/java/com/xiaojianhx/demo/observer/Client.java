package com.xiaojianhx.demo.observer;

public class Client {

    public static void main(final String[] args) {

        ConcreteSubject obj = new ConcreteSubject();
        obj.setTemperature((float) 20.00);
        System.out.println(obj.getTemperature());

        Observer o = new ConcreteObserver(obj);
        o.update(22);
//        obj.setTemperature((float) 21.00);
        System.out.println(obj.getTemperature());
    }
}