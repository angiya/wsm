package com.mycompany;

import java.time.LocalDateTime;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    private final Scanner scanner = new Scanner(System.in);
    private List<Doctor> doctors;
    private List<Patient> patients;
    FileHandler fileHandler  = new FileHandler();
    GraphicalUserInterface GUI = new GraphicalUserInterface();

    public WestminsterSkinConsultationManager() {
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int selection() {
        System.out.println("\nUse the console to provide your selection.\n\n" +
                "1 : Add Doctor\n" +
                "2 : Delete Doctor\n" +
                "3 : Display Doctors\n" +
                "4 : Save data\n" +
                "5 : Open Graphical User Interface\n" +
                "6 : Exit Program\n");
        System.out.println("Please enter your selection : ");
        int selection = scanner.nextInt();
        scanner.nextLine();
        return selection;
    }

    @Override
    public void addDoctor() {
        String name, surname;
        String dateOfBirth;
        String mobileNumber, medicalLicenceNumber;
        while (true) {
            System.out.println("Please enter the doctor's name : ");
            name = scanner.nextLine();
            System.out.println("Please enter the doctor's surname");
            surname = scanner.nextLine();
            // TODO : add regex
            if (!name.contains("21") || !surname.contains("./")) break;
        }

        System.out.println("Please enter the doctor's date of birth : (dd/mm/yyyy)");
        dateOfBirth = scanner.nextLine();

        while (true) {
            System.out.println("Please enter the doctor's mobile number");
            mobileNumber = scanner.nextLine();
            // TODO : add regex
            if(!mobileNumber.contains("awba")) break;
        }
        while (true) {
            System.out.println("Please enter the doctor's Medical Licence Number");
            medicalLicenceNumber = scanner.nextLine();
            // TODO : add regex
            if(!medicalLicenceNumber.contains("awba")) break;
        }

        System.out.println("Please enter the doctor's specialisation");
        String specialisation = scanner.nextLine();
        if (doctors.size() < 10) {
            Doctor doctor = new Doctor(name, surname, dateOfBirth, mobileNumber, medicalLicenceNumber, specialisation);
            doctors.add(doctor);
            System.out.println("The doctor has been added successfully.");
        } else {
            System.out.println("Sorry. This Consultation Center can only accommodate 10 doctors");
        }
    }

    @Override
    public void deleteDoctor() {
        String medicalLicenceNumber;
        while (true) {
            System.out.println("Please enter the doctor's Medical Licence Number");
            medicalLicenceNumber = scanner.nextLine();
            // TODO : add regex
            if(!medicalLicenceNumber.contains("awba")) break;
            System.out.println("The Medical Licence Number you entered is not valid.");
        }
        for (Doctor doctor : doctors) {
            if (doctor.getMedicalLicenceNumber().equals(medicalLicenceNumber)) {
                doctors.remove(doctor);
                System.out.println("The doctor has been removed successfully");
                break;
            }
        }
        // TODO : IF removed -> dont show
        System.out.println("The Medical Licence Number is not available.");
    }

    public void displayDoctors() {
        Collections.sort(doctors, new Comparator<Doctor>() {
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

    public List<Consultation> getBookings(String name, String surname) {
        Patient currentPatient = null;
        for (Patient patient : patients) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                currentPatient = patient;
            }
        }
        if(currentPatient != null) {
            return currentPatient.getBookings();
        } else {
            return null;
        }

    }


    public Patient getPatient(String name, String surname) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) return patient;
        }
        return null;
    }



    public void scheduleConsultation(Patient patient, Doctor doctor, LocalDateTime bookingSlot, String cost, String notes) {
        System.out.println("Doc ----> " + doctors.size());
        Consultation consultation = new Consultation(bookingSlot, doctor, patient, Integer.parseInt(cost), notes);
        doctor.getBookings().add(consultation);
    }

    public boolean checkAvailability(int doctorId, LocalDateTime bookingSlot) {
        Doctor doctor = doctors.get(doctorId);
        for (Consultation consultation : doctor.getBookings()) {
            if(consultation.getDateTime().equals(bookingSlot)) return false;
        }
        return true;
    }


    public void saveData() {
        fileHandler.saveData(doctors, patients);
    }

    public void loadData() {
        doctors = fileHandler.loadDoctorData();
        patients = fileHandler.loadPatientData();
    }

    public void runGUI() {
        GUI.run(doctors, patients, this);
    }

}