// DAO.java
package com.cineman.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DAO {
    protected Connection con;

    protected DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // chỉnh thông số DB của bạn
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cineman?useSSL=false&serverTimezone=UTC",
                    "root", "123456");
        } catch (Exception e) {
            throw new RuntimeException("DB connection failed", e);
        }
    }
}
