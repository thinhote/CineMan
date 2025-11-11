// SearchMovieServlet.java  (Module 1)
package com.cineman.servlet;

import com.cineman.dao.MovieDAO;
import com.cineman.dao.ScreeningDAO;
import com.cineman.entity.Movie;
import com.cineman.entity.Screening;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search-movie")
public class SearchMovieServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private ScreeningDAO screeningDAO;

    @Override public void init() {
        movieDAO = new MovieDAO();
        screeningDAO = new ScreeningDAO();
    }

    /** Hiển thị form + kết quả tìm kiếm theo keyword */
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        if (q != null && !q.isBlank()) {
            List<Movie> movies = movieDAO.findByTitle(q.trim());
            req.setAttribute("movies", movies);
        }
        else {
            List<Movie>  movies = movieDAO.allMovies();
            req.setAttribute("movies", movies);
        }
        req.getRequestDispatcher("/WEB-INF/views/gdSearchMovie.jsp").forward(req, resp);
    }

    /** Trang chi tiết phim + các suất chiếu */
//    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String movieIdStr = req.getParameter("movieId");
//        if (movieIdStr == null) {
//            resp.sendRedirect(req.getContextPath() + "/search-movie");
//            return;
//        }
//        int movieId = Integer.parseInt(movieIdStr);
//        Movie m = movieDAO.findById(movieId);
//        List<Screening> screenings = screeningDAO.getScreeningByMovie(movieId);
//        req.setAttribute("movie", m);
//        req.setAttribute("screenings", screenings);
//        req.getRequestDispatcher("/WEB-INF/views/gdMovieDetails.jsp").forward(req, resp);
//    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId"); // đảm bảo JSP gửi name="movieId"
        if (movieIdStr == null || movieIdStr.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/search-movie");
            return;
        }

        int movieId;
        try {
            movieId = Integer.parseInt(movieIdStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/search-movie");
            return;
        }

        Movie movie = movieDAO.getMovieInfo(movieId);
        if (movie == null) {
            resp.sendRedirect(req.getContextPath() + "/search-movie");
            return;
        }

        req.setAttribute("movie", movie);
        req.getRequestDispatcher("/WEB-INF/views/gdMovieDetails.jsp").forward(req, resp);
    }
}
