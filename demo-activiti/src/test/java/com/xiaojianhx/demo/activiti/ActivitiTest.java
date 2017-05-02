package com.xiaojianhx.demo.activiti;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaojianhx.demo.activiti.service.ProcessService;

public class ActivitiTest extends MainTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessService service;

    @Test
    public void testStart() throws Exception {

        ProcessInstance pi = service.start();
        logger.info(pi.getId());

        List<Task> taskList = service.taskList();

        show(taskList);

        taskList = service.taskList("b");
        Task task = taskList.get(0);
        logger.info(task.getId());
        show(service.taskList("b"));
        service.next(task);
        show(service.taskList("b"));
        show(service.taskList("c"));
    }

    private void show(List<Task> taskList) {
        taskList.forEach(action -> {
            System.out.println(action + "," + action.getAssignee());
        });
        System.out.println("----------------------------------------------");
    }
}
