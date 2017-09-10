package com.xiaojianhx.demo.spring.aop.aspect;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogConfigAspect {

    private static Logger log = LogManager.getLogger(LogConfigAspect.class);

    public void before(JoinPoint point) {
        log.info(Arrays.deepToString(point.getArgs()));
        log.info(Thread.currentThread() + ":before");
    }

    public void after() {
        log.info(Thread.currentThread() + ":after");
    }

    public void around(ProceedingJoinPoint joinpoint) {
        log.info(Thread.currentThread() + ":around");
    }

    public void afterReturning() {
        log.info(Thread.currentThread() + ":afterReturning");
    }

    public void afterThrowing(JoinPoint point) {
        log.info(Thread.currentThread() + ":afterThrowing");
    }
}