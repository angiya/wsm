package com.mycompany;

public class Person {
    private String name;
    private String surname;
    private String dateOfBirth;
    private String mobileNumber;

    public Person() {
    }

    public Person(String name, String surname, String dob, String mobileNumber) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
