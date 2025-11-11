// Seat.java
package com.cineman.entity;

public class Seat {
    private int seatId;
    private int roomId;
    private String seatNumber;

    public Seat() {}

    public int getSeatId() {
        return seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
