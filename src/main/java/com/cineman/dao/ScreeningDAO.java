// ScreeningDAO.java
package com.cineman.dao;

import com.cineman.entity.Screening;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ScreeningDAO extends DAO {

    /** Lấy các suất chiếu theo movieId (dùng ở trang chi tiết phim) */
    public List<Screening> getScreeningByMovie(int movieId) {
        String sql = """
            SELECT id, showDate, showTime, tbScheduleId, tbMovieId, tbRoomId
            FROM tblScreening
            WHERE tbMovieId = ? AND showDate >= CURDATE()
            ORDER BY showDate, showTime
        """;
        List<Screening> list = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Screening s = new Screening();
                    s.setScreeningId(rs.getInt("id"));
                    s.setShowDate(rs.getDate("showDate").toLocalDate());
                    s.setShowTime(rs.getTime("showTime").toLocalTime());
                    s.setScheduleId(rs.getInt("tbScheduleId"));
                    s.setMovieId(rs.getInt("tbMovieId"));
                    s.setRoomId(rs.getInt("tbRoomId"));
                    list.add(s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
