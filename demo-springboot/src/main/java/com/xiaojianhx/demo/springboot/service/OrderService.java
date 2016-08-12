package com.xiaojianhx.demo.springboot.service;

import com.xiaojianhx.demo.springboot.db.entity.Order;

public interface OrderService {

    void insert(Order order);

    Order get(String sn);
}