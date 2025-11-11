// TransactionServlet.java  (Module 2 - chi tiết giao dịch 1 KH)
package com.cineman.servlet;

import com.cineman.dao.CustomerDAO;
import com.cineman.dao.TransactionDAO;
import com.cineman.entity.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

@WebServlet("/customer-transactions")
public class TransactionServlet extends HttpServlet {
    private TransactionDAO transactionDAO;
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        transactionDAO = new TransactionDAO();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int customerId = Integer.parseInt(req.getParameter("cid"));
        String startParam = req.getParameter("start");
        String endParam = req.getParameter("end");

        LocalDate start = null, end = null;
        try {
            if (startParam != null && endParam != null &&
                    !startParam.isBlank() && !endParam.isBlank()) {
                start = LocalDate.parse(startParam);
                end = LocalDate.parse(endParam);
            }
        } catch (Exception ignored) {}

        List<Transaction> transactions =
                transactionDAO.getTransactionsByCustomer(customerId, start, end);
        String customerName = customerDAO.getCustomerName(customerId);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        List<Map<String, Object>> txRows = new ArrayList<>();
        for (Transaction t : transactions) {
            Map<String, Object> row = new HashMap<>();
            row.put("date", t.getDate().format(fmt));
//            row.put("time", t.getPaymentTime());
            row.put("time", t.getPaymentTime().format(timeFmt));
            row.put("orderCode", t.getOrderCode());
            row.put("amount", t.getAmount());
            row.put("method", t.getPaymentMethod());
            txRows.add(row);
        }
        double totalAmount = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();

        req.setAttribute("customerName", customerName);
        req.setAttribute("txRows", txRows);
        req.setAttribute("totalAmount", totalAmount);
        req.getRequestDispatcher("/WEB-INF/views/gdCustomerDetails.jsp")
                .forward(req, resp);
    }
}
