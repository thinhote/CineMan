// Ticket.java
package com.cineman.entity;

public class Ticket {
    private int ticketId;
    private String seatNumber;
    private double price;
    private String ticketType;
    private int screeningId;
    private Integer customerId; // null nếu mua tại quầy không ghi nhận KH

    public Ticket() {}

    public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getTicketType() {
        return ticketType;
    }
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    public int getScreeningId() {
        return screeningId;
    }
    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
