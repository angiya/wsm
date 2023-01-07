package com.mycompany;

import java.time.LocalDateTime;

public interface SkinConsultationManager {
    void addDoctor();
    void deleteDoctor();
    void displayDoctors();
    Patient getPatient(String name, String surname);
    void scheduleConsultation(Patient patient, Doctor doctor, LocalDateTime bookingSlot, String cost, String notes);
    void saveData();
    void loadData();
    void runGUI();

//    void cancelConsultation(Consultation consultation);
//    void printDoctors();
//    void saveDoctors();
//    void printConsultations();
//    void saveConsultations();
}