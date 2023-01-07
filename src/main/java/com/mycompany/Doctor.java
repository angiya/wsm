package com.mycompany;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String medicalLicenceNumber;
    private String specialisation;
    private List<Consultation> bookings;

    public Doctor() {
        super();
        bookings = new ArrayList<>();
    }

    public Doctor(String name, String surname, LocalDate dateOfBirth, String mobileNumber, String medicalLicenceNumber, String specialisation) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
        bookings = new ArrayList<>();
    }

    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public List<Consultation> getBookings() {
        return bookings;
    }

    public void setBookings(List<Consultation> bookings) {
        this.bookings = bookings;
    }
}