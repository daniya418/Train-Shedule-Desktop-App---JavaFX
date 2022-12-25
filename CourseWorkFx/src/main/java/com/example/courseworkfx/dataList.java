package com.example.courseworkfx;

public class dataList {
    public String departureTime;
    public String arrivalTime;
    public String availlable;
    public String booking;
    public Integer avaSeats;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAvaillable() {
        return availlable;
    }

    public void setAvaillable(String availlable) {
        this.availlable = availlable;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public Integer getAvaSeats() {
        return avaSeats;
    }

    public void setAvaSeats(Integer avaSeats) {
        this.avaSeats = avaSeats;
    }

    public dataList(String departureTime, String arrivalTime, String availlable, String booking, Integer avaSeats) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availlable = availlable;
        this.booking = booking;
        this.avaSeats = avaSeats;
    }




}
