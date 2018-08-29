package com.xiaojianhx.demo.quartz.job;

import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Job2 {

    public void execute() throws JobExecutionException {
        System.out.println(Thread.currentThread().getName() + " --> 任务2");
    }
}