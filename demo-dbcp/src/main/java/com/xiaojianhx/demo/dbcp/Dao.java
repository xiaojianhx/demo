package com.xiaojianhx.demo.dbcp;

import java.sql.Connection;

public class Dao {

    private Connection conn;

    public Dao() {
        conn = ConnectionPoolFactory.getInstance().getConnection();
    }
}