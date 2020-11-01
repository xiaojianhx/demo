package com.xiaojianhx.demo.designpattern.observer;

/**
 * 被观察者
 *
 * @author xiaojianhx
 * @version V1.0.0 $ 2020-09-16 11:13:04 init ---- xiaojianhx
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}