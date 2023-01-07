package com.mycompany;

import java.time.LocalDateTime;

public interface SkinConsultationManager {
    void addDoctor();
    void deleteDoctor();
    void displayDoctors();
    void createConsultation(Patient patient, Doctor doctor, LocalDateTime bookingSlot, String cost, String notes);
    boolean checkAvailability(int doctorId, LocalDateTime bookingSlot);
    void saveData();
    void loadData();
    void runGUI();
}