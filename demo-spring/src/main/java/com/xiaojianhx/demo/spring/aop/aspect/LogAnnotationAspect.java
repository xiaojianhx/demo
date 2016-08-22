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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAnnotationAspect {

    private static Logger log = LoggerFactory.getLogger(LogAnnotationAspect.class);

    @Pointcut("execution(* com.xiaojianhx.demo.spring.aop.service.*.*(..))")
    public void aspect() {
        log.info(Thread.currentThread() + ":aspect");
    }

    @Before("aspect()")
    public void before(JoinPoint point) {

        log.info(Arrays.deepToString(point.getArgs()));
        log.info(Thread.currentThread() + ":before");
    }

    @After("aspect()")
    public void after() {
        log.info(Thread.currentThread() + ":after");
    }

    @Around("aspect()")
    public void around(ProceedingJoinPoint joinpoint) {
        log.info(Thread.currentThread() + ":around");
    }

    @AfterReturning("aspect()")
    public void afterReturning() {
        log.info(Thread.currentThread() + ":afterReturning");
    }

    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex) {
        log.info(Thread.currentThread() + ":afterThrowing");
        log.error(ex.getMessage());
    }
}