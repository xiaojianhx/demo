package com.xiaojianhx.demo.activiti.service.impl;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaojianhx.demo.activiti.service.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessEngine processEngine;

    public ProcessInstance start() {
        return processEngine.getRuntimeService().startProcessInstanceByKey("process_leave");
    }

    public List<Task> taskList() {
        return processEngine.getTaskService().createTaskQuery().list();
    }

    public List<Task> taskList(String assignee) {
        return processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
    }

    public void next(Task task) {
        processEngine.getTaskService().complete(task.getId());
    }
}
