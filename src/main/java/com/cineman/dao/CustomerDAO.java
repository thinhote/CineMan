// CustomerDAO.java
package com.cineman.dao;

import java.sql.*;

public class CustomerDAO extends DAO {

    public String getCustomerName(int customerId) {
        String sql = "SELECT name FROM tblUser WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
