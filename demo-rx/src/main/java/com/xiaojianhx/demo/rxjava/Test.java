package com.xiaojianhx.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test {

    public static void main(String[] args) {

        var observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        });

        var observer = new Observer<Integer>() {

            public void onSubscribe(Disposable d) {
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            public void onError(Throwable e) {
                System.out.println("onError : value : " + e.getMessage());
            }

            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
