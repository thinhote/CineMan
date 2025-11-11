// Screening.java
package com.cineman.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Screening {
    private int screeningId;
    private LocalDate showDate;
    private LocalTime showTime;
    private int scheduleId;
    private int movieId;
    private int roomId;

    public Screening() {}

    public int getScreeningId() {
        return screeningId;
    }
    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }
    public LocalDate getShowDate() {
        return showDate;
    }
    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }
    public LocalTime getShowTime() {
        return showTime;
    }
    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
