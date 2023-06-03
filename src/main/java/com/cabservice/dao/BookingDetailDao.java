package com.cabservice.dao;

import com.cabservice.model.Booking;

import java.awt.print.Book;

public class BookingDetailDao {
    private Book  booking;
    private  String cancelUrl;

    //BookingDetailDao(Booking newBooking, String cancelCarUrl) {}



    public BookingDetailDao(Booking newBooking, String cancelCarUrl) {
        this.booking=booking;
        this.cancelUrl=cancelCarUrl;
    }

    public Book getBooking() {
        return booking;
    }

    public void setBooking(Book booking) {
        this.booking = booking;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}
