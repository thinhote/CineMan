// TransactionDAO.java
package com.cineman.dao;

import com.cineman.entity.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO extends DAO {
    /** Lấy tất cả giao dịch của 1 khách hàng trong khoảng ngày (nếu có) */
    public List<Transaction> getTransactionsByCustomer(int customerId, LocalDate start, LocalDate end) {
        List<Transaction> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT id, date, paymentTime, amount, paymentMethod " +
                        "FROM tblTransaction WHERE tblCustomerId = ?"
        );
        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" AND date BETWEEN ? AND ?");
        }
        sql.append(" ORDER BY date DESC, paymentTime DESC");

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            ps.setInt(1, customerId);
            if (hasRange) {
                ps.setDate(2, Date.valueOf(start));
                ps.setDate(3, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction t = new Transaction();
                    int id = rs.getInt("id");
                    t.setTransactionId(rs.getInt("id"));
                    t.setDate(rs.getDate("date").toLocalDate());
                    t.setPaymentTime(rs.getTime("paymentTime").toLocalTime());
                    t.setAmount(rs.getDouble("amount"));
                    t.setPaymentMethod(rs.getString("paymentMethod"));
                    t.setOrderCode(String.format("DH-%04d", id));
                    list.add(t);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
