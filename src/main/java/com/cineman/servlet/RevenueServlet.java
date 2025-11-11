//// RevenueServlet.java  (Module 2 - tổng/ theo KH)
//package com.cineman.servlet;
//
//import com.cineman.dao.CustomerDAO;
//import com.cineman.dao.RevenueDAO;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@WebServlet("/revenue")
//public class RevenueServlet extends HttpServlet {
//    private RevenueDAO revenueDAO;
//    private CustomerDAO customerDAO;
//
//    @Override public void init() {
//        revenueDAO = new RevenueDAO();
//        customerDAO = new CustomerDAO();
//    }
//
//    /** Form chọn khoảng thời gian + bảng tổng hợp doanh thu theo KH */
//    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        LocalDate start = parseOrDefault(req.getParameter("start"), LocalDate.now().minusMonths(1));
//        LocalDate end   = parseOrDefault(req.getParameter("end"), LocalDate.now());
//
//        double total = revenueDAO.getTotalRevenue(start, end);
//        Map<Integer, Double> byCustomer = revenueDAO.getRevenueByCustomer(start, end);
//
//        // map id -> (name, total)
//        Map<String, Double> viewMap = new LinkedHashMap<>();
//        byCustomer.forEach((cid, amt) -> {
//            String name = customerDAO.getCustomerName(cid);
//            viewMap.put(name == null ? ("Customer #" + cid) : name, amt);
//        });
//
//        req.setAttribute("totalRevenue", total);
//        req.setAttribute("start", start);
//        req.setAttribute("end", end);
//        req.setAttribute("revenueByCustomer", viewMap);
//        req.getRequestDispatcher("/WEB-INF/views/gdRevenueStatistics.jsp").forward(req, resp);
//    }
//
//    private LocalDate parseOrDefault(String s, LocalDate d) {
//        try { return (s == null || s.isBlank()) ? d : LocalDate.parse(s); }
//        catch (Exception e) { return d; }
//    }
//}

// RevenueServlet.java
package com.cineman.servlet;

import com.cineman.dao.RevenueDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@WebServlet("/revenue")
public class RevenueServlet extends HttpServlet {
    private RevenueDAO revenueDAO;

    @Override
    public void init() {
        revenueDAO = new RevenueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String startParam = req.getParameter("start");
        String endParam   = req.getParameter("end");

        LocalDate start = null;
        LocalDate end   = null;

        try {
            if (startParam != null && !startParam.isBlank()
                    && endParam != null && !endParam.isBlank()) {
                start = LocalDate.parse(startParam);
                end   = LocalDate.parse(endParam);
            }
        } catch (Exception e) {
            start = null;
            end   = null;
        }

        double total = revenueDAO.getTotalRevenue(start, end);

        Map<java.sql.Date, Double> revenueByDate = revenueDAO.getRevenueByDate(start, end);

        req.setAttribute("totalRevenue", total);
        req.setAttribute("revenueByDate", revenueByDate);
        req.setAttribute("start", startParam);
        req.setAttribute("end", endParam);

        req.getRequestDispatcher("/WEB-INF/views/gdRevenueStatistics.jsp")
                .forward(req, resp);
    }
}
