package com.xiaojianhx.demo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.xiaojianhx.demo.springboot.db.entity.Order;
import com.xiaojianhx.demo.springboot.db.mapper.OrderMapper;
import com.xiaojianhx.demo.springboot.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void insert(Order order) {

        TransactionStatus status = null;

        try {

            TransactionDefinition definition = new DefaultTransactionDefinition();
            status = transactionManager.getTransaction(definition);

            mapper.insert(order);

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Order get(String sn) {
        return mapper.getBySn(sn);
    }
}