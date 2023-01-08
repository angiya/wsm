package com.mycompany;

import java.time.LocalDateTime;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    private static List<Doctor> doctors;
    private static List<Patient> patients;
    private List<Consultation> consultations;

    final FileHandler fileHandler  = new FileHandler();
    final GraphicalUserInterface GUI = new GraphicalUserInterface();


    public WestminsterSkinConsultationManager() {
    }


    public WestminsterSkinConsultationManager(String[] args) {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        consultations = new ArrayList<>();
    }


    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    @Override
    public void addDoctor(Doctor doctor) {
      if (doctors.size() < 10) {
            doctors.add(doctor);
            System.out.println("The doctor has been added successfully.");
        } else {
            System.out.println("Sorry. This Consultation Center can only accommodate 10 doctors.");
        }
    }


    @Override
    public void deleteDoctor(Doctor doctor) {
        doctors.remove(doctor);
        System.out.println("The doctor has been removed successfully");
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
    public void createConsultation(Consultation consultation, Patient patient, Doctor doctor) {
        doctor.getBookings().add(consultation);
        patient.getBookings().add(consultation);
        consultations.add(consultation);
    }


    @Override
    public boolean checkAvailability(int doctorId, LocalDateTime bookingSlot) {
        Doctor doctor = null;
        for (Doctor d : getDoctors()) {
            if (d.getMedicalLicenceNumber().equals(String.valueOf(doctorId))) {
                doctor = d;
                break;
            }
        }
        if (doctor != null) {
            for (Consultation consultation : doctor.getBookings()) {
                if(consultation.getDateTime().equals(bookingSlot)) return false;
            }
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