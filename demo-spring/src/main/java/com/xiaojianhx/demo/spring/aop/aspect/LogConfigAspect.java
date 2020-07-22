package com.xiaojianhx.demo.spring.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogConfigAspect {

    public void before(JoinPoint point) {
        System.out.println(Arrays.deepToString(point.getArgs()));
        System.out.println(Thread.currentThread() + ":before");
    }

    public void after() {
        System.out.println(Thread.currentThread() + ":after");
    }

    public void around(ProceedingJoinPoint joinpoint) {
        System.out.println(Thread.currentThread() + ":around");
    }

    public void afterReturning() {
        System.out.println(Thread.currentThread() + ":afterReturning");
    }

    public void afterThrowing(JoinPoint point) {
        System.out.println(Thread.currentThread() + ":afterThrowing");
    }
}