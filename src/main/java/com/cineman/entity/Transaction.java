// Transaction.java
package com.cineman.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private int transactionId;
    private LocalDate date;
    private LocalTime paymentTime;
    private double amount;
    private int customerId;
    private Integer revenueId;
    private String paymentMethod;
    private String orderCode;

    public Transaction() {}

    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getPaymentTime() { return paymentTime; }
    public void setPaymentTime(LocalTime paymentTime) { this.paymentTime = paymentTime; }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Integer getRevenueId() {
        return revenueId;
    }
    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
}
