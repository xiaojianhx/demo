package com.xiaojianhx.demo.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiInitTest extends MainTest {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    public void init() {

        ProcessEngineConfiguration config = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("applicationContext.xml");
        config.buildProcessEngine();
    }

    @Test
    public void createProcess() {

        Deployment deployment = processEngine.getRepositoryService() // 与流程定义和部署对象相关的Service
                .createDeployment() // 创建一个部署对象
                .name("请假流程") // 设置对应流程的名称
                .addClasspathResource("diagrams/process_leave.bpmn") //
                .deploy(); // 完成部署

        System.out.println("部署Id：" + deployment.getId()); // 部署Id：20001
        System.out.println("部署名称：" + deployment.getName()); // 部署名称：流程定义
    }
}
