package com.xiaojianhx.demo.activiti;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaojianhx.demo.activiti.service.ProcessService;

public class ActivitiProcessTest extends MainTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessService service;

    @Test
    public void testProcess() throws Exception {

        ProcessInstance pi = service.start();
        logger.info(pi.getId());

        logger.info("a发起一个申请后,数据列表:");
        show(service.taskList());

        List<Task> bList = service.taskList("b");
        Task task = bList.get(0);

        service.save(task);
        logger.info("b保存了" + task.getId() + "后,数据列表:");
        show(service.taskList());

        service.complete(task);
        logger.info("b审核了" + task.getId() + "后,数据列表:");
        show(service.taskList());
    }

    private void show(List<Task> data) {
        data.forEach(task -> System.out.println(
                task.getProcessInstanceId() + "," + task.getId() + "," + task.getName() + "," + task.getOwner() + "," + task.getAssignee() + ","));
        System.out.println("----------------------------------------------");
    }
}
