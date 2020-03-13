package com.xiaojianhx.demo.quartz.config;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.lang.reflect.Method;

public class InvokingJobDetailFactory extends QuartzJobBean {

    private String targetObject;

    private String targetMethod;

    private ApplicationContext ctx;

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            var obj = ctx.getBean(targetObject);
            Method m = null;
            try {
                m = obj.getClass().getMethod(targetMethod);
                m.invoke(obj);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }
}