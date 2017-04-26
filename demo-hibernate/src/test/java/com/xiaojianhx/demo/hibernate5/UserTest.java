package com.xiaojianhx.demo.hibernate5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.xiaojianhx.demo.hibernate5.db.entity.User;

public class UserTest {

    private SessionFactory sessionFactory = null;
    private Configuration configuration = new AnnotationConfiguration().configure();
    private Session session;
    private Transaction transaction;

    @Test
    public void test() {

        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        User user = new User();
        session.save(user);
        // session.save(User.class.getName(), user);

        User load = session.load(User.class, 1);
        load = session.load(User.class, 1);
        load = session.get(User.class, 1);

        System.out.println(load);

        session.flush();

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}