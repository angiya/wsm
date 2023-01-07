package com.mycompany;

import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String mobileNumber;

    public Person() {
    }

    public Person(String name, String surname, LocalDate dob, String mobileNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dob;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
