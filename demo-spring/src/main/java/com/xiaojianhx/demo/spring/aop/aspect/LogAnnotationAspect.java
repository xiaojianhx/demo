package com.xiaojianhx.demo.spring.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAnnotationAspect {

    @Pointcut("execution(* com.xiaojianhx.demo.spring.aop.service.*.*(..))")
    public void aspect() {
        System.out.println(Thread.currentThread() + ":aspect");
    }

    @Before("aspect()")
    public void before(JoinPoint point) {
        System.out.println(Arrays.deepToString(point.getArgs()));
        System.out.println(Thread.currentThread() + ":before");
    }

    @After("aspect()")
    public void after() {
        System.out.println(Thread.currentThread() + ":after");
    }

    @Around("aspect()")
    public void around(ProceedingJoinPoint joinpoint) {
        System.out.println(Thread.currentThread() + ":around");
    }

    @AfterReturning("aspect()")
    public void afterReturning() {
        System.out.println(Thread.currentThread() + ":afterReturning");
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex) {
        System.out.println(Thread.currentThread() + ":afterThrowing");
        System.out.println(ex.getMessage());
    }
}