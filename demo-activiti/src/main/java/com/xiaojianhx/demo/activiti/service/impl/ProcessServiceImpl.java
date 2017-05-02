package com.xiaojianhx.demo.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
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

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applyUserId", "a");
        variables.put("assignee", "b");

        return processEngine.getRuntimeService().startProcessInstanceByKey("process_leave", variables);
    }

    public List<Task> taskList() {
        return processEngine.getTaskService().createTaskQuery().list();
    }

    public List<Task> taskList(String assignee) {
        return processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
    }

    public void complete(Task task) {
        processEngine.getTaskService().complete(task.getId());
    }

    public void save(Task task) {
        processEngine.getTaskService().saveTask(task);
    }

    public List<HistoricActivityInstance> history(String assignee) {
        return processEngine.getHistoryService().createHistoricActivityInstanceQuery().taskAssignee(assignee).list();
    }

    public List<HistoricProcessInstance> include(String userId) {
        return processEngine.getHistoryService().createHistoricProcessInstanceQuery().involvedUser(userId).list();
    }
}
