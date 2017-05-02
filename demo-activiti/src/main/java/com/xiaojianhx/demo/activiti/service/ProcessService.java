package com.xiaojianhx.demo.activiti.service;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface ProcessService {

    ProcessInstance start();

    List<Task> taskList();

    List<Task> taskList(String assignee);

    void next(Task task);
}