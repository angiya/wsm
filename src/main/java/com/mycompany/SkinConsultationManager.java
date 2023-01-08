package com.mycompany;

import java.time.LocalDateTime;

public interface SkinConsultationManager {
    void addDoctor(Doctor doctor);
    void deleteDoctor(Doctor doctor);
    void displayDoctors();
    void createConsultation(Consultation consultation, Patient patient, Doctor doctor);
    boolean checkAvailability(int doctorId, LocalDateTime bookingSlot);
    void saveData();
    void loadData();
    void runGUI();
}