package com.xiaojianhx.demo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaojianhx.demo.springboot.db.entity.Order;
import com.xiaojianhx.demo.springboot.service.OrderService;

@SpringBootApplication
@RestController
@MapperScan("com.xiaojianhx.demo.springboot.db.mapper")
public class Application {

    @Autowired
    private OrderService service;

    @RequestMapping("/order/{sn}")
    public Order get(@PathVariable("sn") String sn) {
        return service.get(sn);
    }

    @RequestMapping("/order/create")
    public Order create(String sn) {
        Order order = new Order();
        order.setSn(sn);
        service.insert(order);
        return order;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}