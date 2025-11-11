//// RevenueDAO.java
//package com.cineman.dao;
//
//import com.cineman.entity.Revenue;
//
//import java.sql.*;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.*;
//
//public class RevenueDAO extends DAO {
//
//    /** Tổng doanh thu trong khoảng ngày (Module 2) */
//    public double getTotalRevenue(LocalDate start, LocalDate end) {
//        String sql = "SELECT COALESCE(SUM(amount),0) AS total FROM tblTransaction WHERE date BETWEEN ? AND ?";
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setDate(1, Date.valueOf(start));
//            ps.setDate(2, Date.valueOf(end));
//            try (ResultSet rs = ps.executeQuery()) {
//                return rs.next() ? rs.getDouble("total") : 0d;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** Doanh thu theo từng khách hàng (để hiển thị danh sách top/chi tiết) */
//    public Map<Integer, Double> getRevenueByCustomer(LocalDate start, LocalDate end) {
//        String sql = """
//          SELECT tblCustomerId AS cid, SUM(amount) total
//          FROM tblTransaction
//          WHERE date BETWEEN ? AND ?
//          GROUP BY tblCustomerId
//          ORDER BY total DESC
//        """;
//        Map<Integer, Double> map = new LinkedHashMap<>();
//        try (PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setDate(1, Date.valueOf(start));
//            ps.setDate(2, Date.valueOf(end));
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) map.put(rs.getInt("cid"), rs.getDouble("total"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return map;
//    }
//}

// RevenueDAO.java
package com.cineman.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RevenueDAO extends DAO {

    /** Tổng doanh thu trong khoảng ngày.
     *  Nếu start hoặc end = null -> lấy toàn bộ */
    public double getTotalRevenue(LocalDate start, LocalDate end) {
        StringBuilder sql = new StringBuilder(
                "SELECT COALESCE(SUM(amount),0) AS total FROM tblTransaction"
        );

        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" WHERE date BETWEEN ? AND ?");
        }

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            if (hasRange) {
                ps.setDate(1, Date.valueOf(start));
                ps.setDate(2, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("total") : 0d;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Doanh thu theo từng ngày (để hiển thị bảng thống kê) */
    public Map<java.sql.Date, Double> getRevenueByDate(LocalDate start, LocalDate end) {
        StringBuilder sql = new StringBuilder(
                "SELECT date, SUM(amount) AS total " +
                        "FROM tblTransaction"
        );

        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" WHERE date BETWEEN ? AND ?");
        }
        sql.append(" GROUP BY date ORDER BY date DESC");

        Map<java.sql.Date, Double> map = new LinkedHashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            if (hasRange) {
                ps.setDate(1, Date.valueOf(start));
                ps.setDate(2, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    java.sql.Date sqlDate = rs.getDate("date"); // ✅ để nguyên sql.Date
                    double total = rs.getDouble("total");
                    map.put(sqlDate, total);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    /** Doanh thu theo từng khách hàng (tổng tiền) */
    public Map<Integer, Double> getRevenueByCustomer(LocalDate start, LocalDate end) {
        StringBuilder sql = new StringBuilder(
                "SELECT tblCustomerId AS cid, SUM(amount) AS total " +
                        "FROM tblTransaction"
        );
        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" WHERE date BETWEEN ? AND ?");
        }
        sql.append(" GROUP BY tblCustomerId ORDER BY total DESC");

        Map<Integer, Double> map = new LinkedHashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            if (hasRange) {
                ps.setDate(1, Date.valueOf(start));
                ps.setDate(2, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    map.put(rs.getInt("cid"), rs.getDouble("total"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    /** Doanh thu theo khách hàng kèm ngày giao dịch gần nhất */
    public List<Map<String, Object>> getCustomerRevenueWithDate(LocalDate start, LocalDate end) {
        StringBuilder sql = new StringBuilder(
                "SELECT t.tblCustomerId AS cid, " +
                        "       SUM(t.amount) AS total, " +
                        "       COUNT(*) AS orders, " +
                        "       MAX(t.date) AS last_date " +
                        "FROM tblTransaction t"
        );

        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" WHERE t.date BETWEEN ? AND ?");
        }
//        sql.append(" GROUP BY t.tblCustomerId ORDER BY total DESC");
        sql.append(" GROUP BY t.tblCustomerId ORDER BY MAX(t.date) DESC, SUM(t.amount) DESC");

        List<Map<String, Object>> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            if (hasRange) {
                ps.setDate(1, Date.valueOf(start));
                ps.setDate(2, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("customerId", rs.getInt("cid"));
                    row.put("amount", rs.getDouble("total"));
                    row.put("orders", rs.getInt("orders"));
                    row.put("lastDate", rs.getDate("last_date")); // ✅ thêm ngày gần nhất
                    list.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /** Số giao dịch (đơn hàng) theo từng khách hàng */
    public Map<Integer, Integer> getOrderCountByCustomer(LocalDate start, LocalDate end) {
        StringBuilder sql = new StringBuilder(
                "SELECT tblCustomerId AS cid, COUNT(*) AS orders " +
                        "FROM tblTransaction"
        );
        boolean hasRange = (start != null && end != null);
        if (hasRange) {
            sql.append(" WHERE date BETWEEN ? AND ?");
        }
        sql.append(" GROUP BY tblCustomerId");

        Map<Integer, Integer> map = new LinkedHashMap<>();
        try (PreparedStatement ps = con.prepareStatement(sql.toString())) {
            if (hasRange) {
                ps.setDate(1, Date.valueOf(start));
                ps.setDate(2, Date.valueOf(end));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    map.put(rs.getInt("cid"), rs.getInt("orders"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
