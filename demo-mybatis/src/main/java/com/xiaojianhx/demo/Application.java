package com.xiaojianhx.demo;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xiaojianhx.demo.mybatis.db.entity.Buyer;
import com.xiaojianhx.demo.mybatis.db.entity.User;

public class Application {

    public static void main(String[] args) {

        InputStream is = Application.class.getClassLoader().getResourceAsStream("config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();

        // User user =
        // session.selectOne("com.xiaojianhx.demo.mybatis.db.dao.UserMapper.get",
        // 1);
        // System.out.println(user.getId());

        Buyer buyer = session.selectOne("com.xiaojianhx.demo.mybatis.db.dao.BuyerMapper.get", 1);
        System.out.println(buyer.getId());
        System.out.println(buyer.getUser().getUsername());
    }
}
