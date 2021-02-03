package com.xiaojianhx.demo.rxjava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Test {

    private static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        test();

        logger.info("run here");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void test() {

        var observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                logger.info("emitter:{}", 1);
                e.onNext(1);
                Thread.sleep(5000);
                logger.info("emitter:{}", 2);
                e.onNext(2);
            }
        }).subscribeOn(Schedulers.io());

        var observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                logger.info("emitter:{}", "a");
                e.onNext("a");
                logger.info("emitter:{}", "b");
                e.onNext("b");
                logger.info("emitter:{}", "c");
                e.onNext("c");
            }
        }).subscribeOn(Schedulers.io());

        var observer = new Observer<String>() {

            public void onSubscribe(Disposable d) {
                logger.info("Disposable:{}", d);
            }

            public void onNext(String str) {
                logger.info(" onNext:{}", str);
            }

            public void onError(Throwable e) {
                logger.info("onError:{}", e);
            }

            public void onComplete() {
                logger.info("onComplete");
            }
        };

        Observable.zip(observable1, observable2, (a, b) -> a + b).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(observer);
    }
}
