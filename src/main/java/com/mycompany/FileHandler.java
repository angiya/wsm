package com.mycompany;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    BufferedReader br;
    static final File doctorFile = new File("doctors.txt");
    static final File patientFile = new File("patients.txt");
    static final File consultationFile = new File("consultations.txt");


    public void saveData(List<Doctor> doctors, List<Patient> patients, List<Consultation> consultations) {
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(doctorFile));
            for (Doctor doctor : doctors) {
                bf.write(doctor.getName() + "|" +
                        doctor.getSurname() + "|" +
                        doctor.getDateOfBirth() + "|" +
                        doctor.getMobileNumber() + "|" +
                        doctor.getMedicalLicenceNumber() + "|" +
                        doctor.getSpecialisation());
                bf.newLine();
            }
            bf.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bf = new BufferedWriter(new FileWriter(patientFile));
            for (Patient patient : patients) {
                bf.write(patient.getId() + "|" +
                        patient.getName() + "|" +
                        patient.getSurname() + "|" +
                        patient.getDateOfBirth() + "|" +
                        patient.getMobileNumber());
                bf.newLine();
            }
            bf.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bf = new BufferedWriter(new FileWriter(consultationFile));
            for (Consultation consultation : consultations) {
                bf.write(consultation.getDateTime() + "|" +
                        consultation.getCost() + "|" +
                        consultation.getNotes() + "|" +
                        consultation.getDoctor().getMedicalLicenceNumber() + "|" +
                        consultation.getPatient().getId());
                bf.newLine();
            }
            bf.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Doctor> loadDoctorData() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            String line;
            br = new BufferedReader(new FileReader(doctorFile));

            while ((line = br.readLine()) != null) {
                Doctor doctor = new Doctor();
                String[] split = line.split("\\|");
                for (int i = 0; i < split.length; i++) {
                    doctor.setName(split[0]);
                    doctor.setSurname(split[1]);
                    doctor.setDateOfBirth(LocalDate.parse(split[2]));
                    doctor.setMobileNumber(split[3]);
                    doctor.setMedicalLicenceNumber(split[4]);
                    doctor.setSpecialisation(split[5]);
                }
                doctors.add(doctor);
            }
        } catch (Exception e) {
            System.out.println("Sorry there was an error in loading the data.");
        }
        return doctors;
    }

    public List<Patient> loadPatientData() {
        List<Patient> patients = new ArrayList<>();
        try {
            String line;
            br = new BufferedReader(new FileReader(patientFile));

            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                Patient patient = new Patient();
                for (int i = 0; i < split.length; i++) {
                    patient.setName(split[0]);
                    patient.setSurname(split[1]);
                    patient.setDateOfBirth(LocalDate.parse(split[2]));
                    patient.setMobileNumber(split[3]);
                    patient.setId(Integer.parseInt(split[4]));
                }
                patients.add(patient);
            }
        } catch (Exception e) {
            System.out.println("Sorry there was an error in loading the data.");
        }
        return patients;
    }


    public List<Consultation> loadConsultationData() {
        List<Consultation> consultations = new ArrayList<>();
        try {
            String line;
            br = new BufferedReader(new FileReader(consultationFile));

            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|");
                Consultation consultation = new Consultation();
                for (int i = 0; i < split.length; i++) {
                    consultation.setDateTime(LocalDateTime.parse(split[0]));
                    consultation.setCost(Integer.parseInt(split[1]));
                    consultation.setNotes(split[2]);
                    consultation.setDoctor(findDoctor(split[3]));
                    consultation.setPatient(findPatient(split[4]));
                }
                consultations.add(consultation);
            }
        } catch (Exception e) {
            System.out.println("Sorry there was an error in loading the data.");
        }
        return consultations;
    }

    private Patient findPatient(String patientId) {
        List<Patient> patients = WestminsterSkinConsultationManager.getPatients();
        for (Patient patient : patients) {
            if (String.valueOf(patient.getId()).equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    private Doctor findDoctor(String medicalLicenseNumber) {
        List<Doctor> doctors = WestminsterSkinConsultationManager.getDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getMedicalLicenceNumber().equals(medicalLicenseNumber)) {
                return doctor;
            }
        }
        return null;
    }
}
