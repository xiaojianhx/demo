package com.xiaojianhx.demo.rxjava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Test {

    private static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {

            public void onSubscribe(Disposable d) {
            }

            public void onNext(Integer integer) {
                logger.debug(integer);
            }

            public void onError(Throwable e) {
                logger.debug("onError : value : " + e.getMessage());
            }

            public void onComplete() {
                logger.debug("onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
