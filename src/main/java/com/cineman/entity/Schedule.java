// Schedule.java
package com.cineman.entity;

import java.time.LocalDate;

public class Schedule {
    private int scheduleId;
    private LocalDate date;

    public Schedule() {}

    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
