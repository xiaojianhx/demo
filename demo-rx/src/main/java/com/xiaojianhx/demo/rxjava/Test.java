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

        var observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            public void subscribe(ObservableEmitter<Integer> e) throws Exception {

                logger.info("emitter:{}", 1);
                e.onNext(1);

                logger.info("emitter:{}", 2);
                e.onNext(2);

                logger.info("emitter:{}", 3);
                e.onNext(3);

                logger.info("emitter:{}", 4);
                e.onNext(4);

                Thread.sleep(3000);

                logger.info("emitter:{}", 5);
                e.onNext(5);
            }
        });

        var observer = new Observer<Integer>() {

            public void onSubscribe(Disposable d) {
                logger.info("Disposable:{}", d);
            }

            public void onNext(Integer integer) {
                logger.info(" onNext:{}", integer);
            }

            public void onError(Throwable e) {
                logger.info("onError:{}", e);
            }

            public void onComplete() {
                logger.info("onComplete");
            }
        };

        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.single()).subscribe(observer);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
