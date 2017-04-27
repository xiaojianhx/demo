package com.xiaojianhx.demo.hibernate5;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

public class AutoTest {

    private Configuration configuration = new Configuration().configure();

    @Test
    public void test() {
        try {
            configuration.buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}