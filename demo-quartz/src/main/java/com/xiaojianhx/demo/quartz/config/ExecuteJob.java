package com.xiaojianhx.demo.quartz.config;

import org.springframework.stereotype.Service;

@Service
public class ExecuteJob {

    public void execute() {
        System.out.println("定时任务执行了。。。。。");
    }
}