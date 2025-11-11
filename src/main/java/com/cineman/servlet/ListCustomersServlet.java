// ListCustomersServlet.java
package com.cineman.servlet;

import com.cineman.dao.CustomerDAO;
import com.cineman.dao.RevenueDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet("/list-customers")
public class ListCustomersServlet extends HttpServlet {

    private RevenueDAO revenueDAO;
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        revenueDAO = new RevenueDAO();
        customerDAO = new CustomerDAO();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        String startParam = req.getParameter("start");
//        String endParam   = req.getParameter("end");
//
//        LocalDate start = null;
//        LocalDate end   = null;
//
//        try {
//            if (startParam != null && !startParam.isBlank()
//                    && endParam != null && !endParam.isBlank()) {
//                start = LocalDate.parse(startParam); // yyyy-MM-dd
//                end   = LocalDate.parse(endParam);
//            }
//        } catch (Exception e) {
//            start = null;
//            end   = null;
//        }
//
//        double totalRevenue = revenueDAO.getTotalRevenue(start, end);
//        Map<Integer, Double> revenueMap = revenueDAO.getRevenueByCustomer(start, end);
//        Map<Integer, Integer> orderMap  = revenueDAO.getOrderCountByCustomer(start, end);
//
//        // Chuẩn bị danh sách row cho JSP
//        List<Map<String, Object>> rows = new ArrayList<>();
//        for (Map.Entry<Integer, Double> entry : revenueMap.entrySet()) {
//            Integer cid = entry.getKey();
//            Double amount = entry.getValue();
//
//            Map<String, Object> row = new HashMap<>();
//            row.put("customerId", cid);
//            row.put("name", customerDAO.getCustomerName(cid));
//            row.put("orders", orderMap.getOrDefault(cid, 0));
//            row.put("amount", amount);
//
//            rows.add(row);
//        }
//
//        req.setAttribute("totalRevenue", totalRevenue);
//        req.setAttribute("start", startParam);
//        req.setAttribute("end", endParam);
//        req.setAttribute("customerRows", rows);
//
//        req.getRequestDispatcher("/WEB-INF/views/gdListCustomers.jsp")
//                .forward(req, resp);
//    }
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

        double totalRevenue = revenueDAO.getTotalRevenue(start, end);
        List<Map<String, Object>> data = revenueDAO.getCustomerRevenueWithDate(start, end);

        // Gắn thêm tên khách hàng
        for (Map<String, Object> row : data) {
            int cid = (Integer) row.get("customerId");
            String name = customerDAO.getCustomerName(cid);
            row.put("name", name);
        }

        req.setAttribute("totalRevenue", totalRevenue);
        req.setAttribute("start", startParam);
        req.setAttribute("end", endParam);
        req.setAttribute("customerRows", data);

        req.getRequestDispatcher("/WEB-INF/views/gdListCustomers.jsp")
                .forward(req, resp);
    }
}
