package com.xiaojianhx.demo.springboot.db.mapper;

import org.apache.ibatis.annotations.Param;

import com.xiaojianhx.demo.springboot.db.entity.Order;

public interface OrderMapper {

    void insert(Order order);

    Order getBySn(@Param("sn") String sn);
}