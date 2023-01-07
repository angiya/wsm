package com.mycompany;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    private static List<Doctor> doctors;
    private static List<Patient> patients;
    private List<Consultation> consultations;

    final InputHandler inputHandler = new InputHandler();
    final FileHandler fileHandler  = new FileHandler();
    final GraphicalUserInterface GUI = new GraphicalUserInterface();


    public WestminsterSkinConsultationManager() {
    }


    public static List<Doctor> getDoctors() {
        return doctors;
    }


    public static List<Patient> getPatients() {
        return patients;
    }


    public int selection() {
        return inputHandler.selection();
    }


    @Override
    public void addDoctor() {
        String name = inputHandler.enterText("name");
        String surname = inputHandler.enterText("surname");
        LocalDate dateOfBirth = inputHandler.enterDateOfBirth();
        String mobileNumber = inputHandler.enterNumber("Mobile");
        String medicalLicenceNumber = inputHandler.enterNumber("Medical License");
        String specialisation = inputHandler.enterText("specialisation");

        if (doctors.size() < 10) {
            Doctor doctor = new Doctor(name, surname, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation);
            doctors.add(doctor);
            System.out.println("The doctor has been added successfully.");
        } else {
            System.out.println("Sorry. This Consultation Center can only accommodate 10 doctors.");
        }
    }


    @Override
    public void deleteDoctor() {
        boolean wasDeleted = false;
        String medicalLicenceNumber =  inputHandler.enterNumber("Medical License");
        for (Doctor doctor : doctors) {
            if (doctor.getMedicalLicenceNumber().equals(medicalLicenceNumber)) {
                doctors.remove(doctor);
                System.out.println("The doctor has been removed successfully");
                wasDeleted = true;
                break;
            }
        }
        if (!wasDeleted) System.out.println("The Medical Licence Number is not available.");
    }


    @Override
    public void displayDoctors() {
        doctors.sort(new Comparator<>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });
        for (Doctor doctor : doctors) {
            System.out.println("Name : " + doctor.getName() + " " + doctor.getSurname() +
                    "\nDate of birth : " + doctor.getDateOfBirth() +
                    "\nMobile Number : " + doctor.getMobileNumber() +
                    "\nMedical Licence Number : " + doctor.getMedicalLicenceNumber() +
                    "\nSpecialisation : " + doctor.getSpecialisation() + "\n");
        }
    }


    @Override
    public void createConsultation(Patient patient, Doctor doctor, LocalDateTime bookingSlot, String cost, String notes) {
        Consultation consultation = new Consultation(bookingSlot, doctor, patient, Integer.parseInt(cost), notes);
        doctor.getBookings().add(consultation);
        patient.getBookings().add(consultation);
        consultations.add(consultation);
    }


    @Override
    public boolean checkAvailability(int doctorId, LocalDateTime bookingSlot) {
        Doctor doctor = doctors.get(doctorId);
        for (Consultation consultation : doctor.getBookings()) {
            if(consultation.getDateTime().equals(bookingSlot)) return false;
        }
        return true;
    }


    @Override
    public void saveData() {
        fileHandler.saveData(doctors, patients, consultations);
    }


    @Override
    public void loadData() {
        doctors = fileHandler.loadDoctorData();
        patients = fileHandler.loadPatientData();
        consultations = fileHandler.loadConsultationData();
    }


    @Override
    public void runGUI() {
        GUI.run(doctors, patients, this);
    }
}