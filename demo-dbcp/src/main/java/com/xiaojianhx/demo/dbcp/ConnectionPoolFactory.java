package com.xiaojianhx.demo.dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnectionPoolFactory {

    private static ConnectionPoolFactory instance = new ConnectionPoolFactory();
    private static DataSource ds;
    private ThreadLocal<Connection> local = new ThreadLocal<>();

    private ConnectionPoolFactory() {

        Properties properties = new Properties();

        try {
            properties.load(ConnectionPoolFactory.class.getResourceAsStream("/database.properties"));
            ds = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPoolFactory getInstance() {

        if (instance == null) {
            synchronized (instance) {
                instance = new ConnectionPoolFactory();
            }
        }
        return instance;
    }

    public Connection getConnection() {

        Connection conn = local.get();

        if (conn != null) {
            return conn;
        }

        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        local.set(conn);
        return conn;
    }
}