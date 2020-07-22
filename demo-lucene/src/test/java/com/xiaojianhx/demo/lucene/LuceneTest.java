package com.xiaojianhx.demo.lucene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xiaojianhx.common.utils.IOUtils;

public class LuceneTest {

    @Test
    public void buildIndexTest() {

        // List<String> data = new ArrayList<>();
        // for (int i = 0; i < 11; i++) {
        // data.add("中国人" + i);
        // }

        List<String> data = getData();

        Lucene.buildIndex(data);
        List<String> result = Lucene.search("六边形");

        System.out.println(result);
    }

    private List<String> getData() {

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://192.168.1.80:3306/app";

        final String USER = "root";
        final String PASS = "123456";

        Connection conn = null;
        Statement stmt = null;

        List<String> data = new ArrayList<>();

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM management_adx_ads ORDER BY ID DESC LIMIT 100");

            while (rs.next()) {
                data.add(rs.getString("adsname"));
            }
            IOUtils.close(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(stmt, conn);
        }

        return data;
    }
}
