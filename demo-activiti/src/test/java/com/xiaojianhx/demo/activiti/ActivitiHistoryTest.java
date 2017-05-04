package com.xiaojianhx.demo.activiti;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Comment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaojianhx.demo.activiti.service.ProcessService;

public class ActivitiHistoryTest extends MainTest {

    private static Format F = new SimpleDateFormat("HH:mm:ss");

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessService service;

    @Test
    public void testProcess() throws Exception {
        List<HistoricProcessInstance> data = service.include("b");
        show(data);
    }

    private void show(List<HistoricProcessInstance> data) {
        data.forEach(hpi -> {
            logger.info("{}, {}, {}, {}", format(hpi.getEndTime()), hpi.getName(), hpi.getProcessVariables(), hpi.getStartUserId());

            List<Comment> commentList = service.comments(hpi.getId());

            commentList.forEach(comment -> {
                logger.info("备注:" + comment.getUserId() + "," + comment.getFullMessage());
            });
        });
    }

    private String format(Date d) {

        if (d == null) {
            return "NULL";
        }
        return F.format(d);
    }
}
