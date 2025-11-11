// MovieDAO.java
package com.cineman.dao;

import com.cineman.entity.Movie;
import java.sql.*;
import java.util.*;

public class MovieDAO extends DAO {

    /** Tìm phim theo tiêu đề (LIKE %keyword%) */
    public List<Movie> findByTitle(String keyword) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT id, title, genre, duration, description FROM tblMovie WHERE title LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie m = new Movie();
                    m.setMovieId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setGenre(rs.getString("genre"));
                    m.setDuration(rs.getString("duration"));
                    m.setDescription(rs.getString("description"));
                    list.add(m);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Movie> allMovies() {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT id, title, genre, duration, description FROM tblMovie";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Movie m = new Movie();
                m.setMovieId(resultSet.getInt("id"));
                m.setTitle(resultSet.getString("title"));
                m.setGenre(resultSet.getString("genre"));
                m.setDuration(resultSet.getString("duration"));
                m.setDescription(resultSet.getString("description"));
                list.add(m);
            }
            return  list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Lấy chi tiết một phim theo id */
    public Movie getMovieInfo(int id) {
        String sql = "SELECT id, title, genre, year, cast, director, duration, description FROM tblMovie WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Movie m = new Movie();
                    m.setMovieId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setGenre(rs.getString("genre"));
                    m.setDuration(rs.getString("duration"));
                    m.setDescription(rs.getString("description"));
                    m.setYear(rs.getString("year"));
                    m.setCast(rs.getString("cast"));
                    m.setDirector(rs.getString("director"));
                    return m;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


//    public Movie getMovieInfo(int movieId) {
//        // có thể tái dùng findById để tránh lặp code
//        return findById(movieId);
//        // hoặc copy y nguyên truy vấn của findById nếu bạn muốn độc lập.
//    }
}
