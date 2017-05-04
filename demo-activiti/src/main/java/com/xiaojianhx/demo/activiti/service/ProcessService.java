package com.xiaojianhx.demo.activiti.service;

import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

public interface ProcessService {

    ProcessInstance start();

    List<Task> taskList();

    List<Task> taskList(String assignee);

    void complete(Task task);

    void save(Task task);

    List<HistoricActivityInstance> history(String assignee);

    List<HistoricProcessInstance> include(String userId);

    List<Comment> comments(String processInstanceId);
}