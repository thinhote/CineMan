// ScheduleDAO.java
package com.cineman.dao;

import com.cineman.entity.Schedule;
import java.sql.*;
import java.time.LocalDate;

public class ScheduleDAO extends DAO {

    /** Lấy lịch (ngày chiếu) theo id */
    public Schedule getSchedule(int scheduleId) {
        String sql = "SELECT id, dateTime FROM tblSchedule WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, scheduleId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Schedule s = new Schedule();
                    s.setScheduleId(rs.getInt("id"));
                    s.setDate(rs.getDate("dateTime").toLocalDate());
                    return s;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
