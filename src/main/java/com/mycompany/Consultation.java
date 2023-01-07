package com.mycompany;


import java.time.LocalDateTime;

public class Consultation {
    private LocalDateTime dateTime;
    private int cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;

    public Consultation(LocalDateTime dateTime, Doctor doctor, Patient patient, int cost, String notes) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.cost = cost;
        this.notes = notes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

