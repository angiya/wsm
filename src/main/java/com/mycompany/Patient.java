package com.mycompany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private int id;
    private List<Consultation> bookings;

    public Patient() {
        super();
        bookings = new ArrayList<>();
    }

    public Patient(String name, String surname, LocalDate dateOfBirth, String mobileNumber, int id) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.id = id;
        bookings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Consultation> getBookings() {
        return bookings;
    }

    public void setBookings(List<Consultation> bookings) {
        this.bookings = bookings;
    }
}
