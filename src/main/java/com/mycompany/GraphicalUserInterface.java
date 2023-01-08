package com.mycompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GraphicalUserInterface {
    static JFrame frame;
    static JTable table;
    static final String[] columns = {"Surname", "Name", "Birthday", "# Number", "MLN", "Spec."};


    public String[][] populateTable(List<Doctor> doctors) {
        String[][] data = new String[doctors.size()][6];
        for (int i = 0; i < doctors.size(); i++) {
            data[i][0] = doctors.get(i).getSurname();
            data[i][1] = doctors.get(i).getName();
            data[i][2] = String.valueOf(doctors.get(i).getDateOfBirth());
            data[i][3] = doctors.get(i).getMobileNumber();
            data[i][4] = doctors.get(i).getMedicalLicenceNumber();
            data[i][5] = doctors.get(i).getSpecialisation();
        }
        return data;
    }


    public Patient getPatient(String name, String surname, List<Patient> patients) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) return patient;
        }
        return null;
    }


    public void run(List<Doctor> doctors, List<Patient> patients, WestminsterSkinConsultationManager manager) {
        String[][] data = populateTable(doctors);
        frame = new JFrame("Westminster Skin Consultation Manager");
        frame.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame.setSize(500, 230);

        /* Creating panels for components */
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tablePanel.setBounds(0,0,500,300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(0,300,400,250);

        /* Creating Table */
        table = new JTable(data, columns);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setBounds(0,0,100,100);
        JScrollPane scrollPane =new JScrollPane(table);
        tablePanel.add(scrollPane);

        /* Creating Buttons */
        JButton bookConsultationButton = new JButton("Book Consultation");
        bookConsultationButton.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex >= 0 && rowIndex < doctors.size()) {
                bookConsultation(patients, doctors, rowIndex, manager);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a doctor by clicking on the desired doctor's row.");
            }
        });
        buttonPanel.add(bookConsultationButton);

        JButton viewBookings =new JButton("View Patient's Bookings");
        viewBookings.addActionListener(e -> findPatient(patients));
        buttonPanel.add(viewBookings);

        frame.add(tablePanel);
        frame.add(buttonPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    private void findPatient(List<Patient> patients) {
        JFrame frame = new JFrame("Patient Information Form");
        frame.setBounds(100, 100, 400, 170);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JLabel name = new JLabel("Name : ");
        name.setFont(new Font("Arial", Font.PLAIN, 13));
        name.setBounds(12, 31, 75, 20);
        frame.getContentPane().add(name);

        JTextField textName = new JTextField();
        textName.setFont(new Font("Arial", Font.PLAIN, 12));
        textName.setSize(190, 20);
        textName.setBounds(100, 28, 275, 20);
        textName.setColumns(10);
        frame.getContentPane().add(textName);

        JLabel surname = new JLabel("Surname : ");
        surname.setFont(new Font("Arial", Font.PLAIN, 12));
        surname.setSize(100, 20);
        surname.setBounds(12, 68, 75, 20);
        frame.getContentPane().add(surname);

        JTextField textSurname = new JTextField();
        textSurname.setFont(new Font("Arial", Font.PLAIN, 12));
        textSurname.setSize(150, 20);
        textSurname.setBounds(100, 68, 275, 20);
        frame.getContentPane().add(textSurname);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        submitButton.setSize(100, 20);
        submitButton.setBounds(250, 100, 100, 20);
        frame.getContentPane().add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Patient patient;
                if(textName.getText().isEmpty()||(textSurname.getText().isEmpty()))
                    JOptionPane.showMessageDialog(null, "Please fill all required details.");
                else {
                    patient = getPatient(textName.getText(), textSurname.getText(), patients);
                    if (patient == null) {
                        JOptionPane.showMessageDialog(null, "The patient name is not valid.");
                    } else {
                        displayBookings(patient);
                    }

                }
            }
        });
    }


    private void displayBookings(Patient patient) {
        List<Consultation> bookings = patient.getBookings();
        String[][] data = new String[patient.getBookings().size()][4];
        String[] columns = {" Date & Time ", " Doctor ", " Cost ", " Notes "};

        bookings.sort(new Comparator<>() {
            @Override
            public int compare(Consultation o1, Consultation o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });

        for (int i = 0; i < bookings.size(); i++) {
            data[i][0] = bookings.get(i).getDateTime().toString();
            data[i][1] = bookings.get(i).getDoctor().getSurname() + " " + bookings.get(i).getDoctor().getName();
            data[i][2] = String.valueOf(bookings.get(i).getCost());
            data[i][3] = bookings.get(i).getNotes();
        }

        JFrame frame = new JFrame("Your Bookings");
        frame.setSize(500,250);
        frame.setResizable(false);
        frame.setVisible(true);

        JTable table = new JTable(data, columns);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane =new JScrollPane(table);
        frame.add(scrollPane);
    }


    private void bookConsultation(List<Patient> patients, List<Doctor> doctors, int rowIndex, WestminsterSkinConsultationManager manager) {
        JFrame frame = new JFrame("Schedule new consultation");
        frame.setBounds(100, 100, 400, 450);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JLabel name = new JLabel("Name : ");
        name.setFont(new Font("Arial", Font.PLAIN, 13));
        name.setBounds(15, 25, 75, 20);
        frame.getContentPane().add(name);

        JTextField textName = new JTextField();
        textName.setFont(new Font("Arial", Font.PLAIN, 12));
        textName.setSize(190, 20);
        textName.setBounds(100, 25, 275, 20);
        textName.setColumns(10);
        frame.getContentPane().add(textName);

        JLabel surname = new JLabel("Surname : ");
        surname.setFont(new Font("Arial", Font.PLAIN, 13));
        surname.setSize(100, 20);
        surname.setBounds(15, 65, 75, 20);
        frame.getContentPane().add(surname);

        JTextField textSurname = new JTextField();
        textSurname.setFont(new Font("Arial", Font.PLAIN, 12));
        textSurname.setSize(150, 20);
        textSurname.setBounds(100, 65, 275, 20);
        frame.getContentPane().add(textSurname);

        JLabel dateOfBirth = new JLabel("Birthday : ");
        dateOfBirth.setFont(new Font("Arial", Font.PLAIN, 13));
        dateOfBirth.setBounds(15, 105, 75, 20);
        frame.getContentPane().add(dateOfBirth);

        JTextField textDob = new JTextField();
        textDob.setFont(new Font("Arial", Font.PLAIN, 12));
        textDob.setBounds(100, 105, 275, 20);
        frame.getContentPane().add(textDob);

        JLabel mobileNumber = new JLabel("Contact no : ");
        mobileNumber.setFont(new Font("Arial", Font.PLAIN, 13));
        mobileNumber.setBounds(15, 145, 75, 20);
        frame.getContentPane().add(mobileNumber);

        JTextField textMobileNumber = new JTextField();
        textMobileNumber.setFont(new Font("Arial", Font.PLAIN, 12));
        textMobileNumber.setBounds(100, 145, 275, 20);
        frame.getContentPane().add(textMobileNumber);

        JLabel notesLabel = new JLabel("Notes : ");
        notesLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        notesLabel.setBounds(15, 185, 75, 20);
        frame.getContentPane().add(notesLabel);

        String notes = "";
        JButton addNotesButton  = new JButton("Add Notes");
        addNotesButton.setFont(new Font("Arial", Font.BOLD, 10));
        addNotesButton.setBounds(160, 185, 100, 20);
        frame.getContentPane().add(addNotesButton);
        addNotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String finalNotes = addNotes();
                JOptionPane.showMessageDialog(null, "Notes added successfully.");
            }
        });

        JLabel cost = new JLabel("Cost : ");
        cost.setFont(new Font("Arial", Font.PLAIN, 13));
        cost.setSize(100, 20);
        cost.setBounds(15, 225, 75, 20);
        frame.getContentPane().add(cost);

        JTextField textCost = new JTextField();
        textCost.setFont(new Font("Arial", Font.PLAIN, 12));
        textCost.setSize(150, 20);
        textCost.setBounds(100, 225, 275, 20);
        frame.getContentPane().add(textCost);

        JLabel bookingDate = new JLabel("Booking Date : ");
        bookingDate.setFont(new Font("Arial", Font.PLAIN, 13));
        bookingDate.setBounds(15, 265, 180, 20);
        frame.getContentPane().add(bookingDate);

        JTextField BookingDateDay = new JTextField();
        BookingDateDay.setFont(new Font("Arial", Font.PLAIN, 12));
        BookingDateDay.setBounds(130, 265, 50, 20);
        BookingDateDay.setColumns(10);
        frame.getContentPane().add(BookingDateDay);

        JTextField BookingDateMonth = new JTextField();
        BookingDateMonth.setFont(new Font("Arial", Font.PLAIN, 12));
        BookingDateMonth.setBounds(190, 265, 50, 20);
        BookingDateMonth.setColumns(10);
        frame.getContentPane().add(BookingDateMonth);

        JTextField BookingDateYear = new JTextField();
        BookingDateYear.setFont(new Font("Arial", Font.PLAIN, 12));
        BookingDateYear.setBounds(250, 265, 50, 20);
        BookingDateYear.setColumns(10);
        frame.getContentPane().add(BookingDateYear);

        JLabel bookingTime = new JLabel("Booking Time : ");
        bookingTime.setFont(new Font("Arial", Font.PLAIN, 13));
        bookingTime.setBounds(15, 305, 180, 20);
        frame.getContentPane().add(bookingTime);

        JTextField textBookingTime = new JTextField();
        textBookingTime.setFont(new Font("Arial", Font.PLAIN, 12));
        textBookingTime.setSize(190, 20);
        textBookingTime.setBounds(130, 305, 50, 20);
        textBookingTime.setColumns(10);
        frame.getContentPane().add(textBookingTime);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        submitButton.setSize(100, 20);
        submitButton.setBounds(150, 350, 80, 20);
        frame.getContentPane().add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Patient patient;
                if(textName.getText().isEmpty()||(textSurname.getText().isEmpty()))
                    JOptionPane.showMessageDialog(null, "Please fill all required details.");
                else {
                    try {
                        LocalDateTime bookingSlot = LocalDateTime.of(Integer.parseInt(BookingDateYear.getText()),
                                Integer.parseInt(BookingDateMonth.getText()),
                                Integer.parseInt(BookingDateMonth.getText()),
                                Integer.parseInt(textBookingTime.getText()), 0);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dateOfBirth = LocalDate.parse(textDob.getText(), formatter);
                        patient = getPatient(textName.getText(), textSurname.getText(), patients);
                        if (patient == null) {
                            patient = new Patient(textName.getText(), textSurname.getText(), dateOfBirth, textMobileNumber.getText(), patients.size() + 1);
                            WestminsterSkinConsultationManager.getPatients().add(patient);
                            scheduleConsultation(patient, rowIndex, bookingSlot, doctors, textCost.getText(), notes, manager);
                        } else {
                            scheduleConsultation(patient, rowIndex, bookingSlot, doctors, "25", notes, manager);
                        }
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Please Enter a valid date.");
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private String addNotes() {
        final String[] notes = {""};
        JFrame frame = new JFrame("Add Notes");
        frame.setBounds(100, 100, 400, 170);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        final JLabel[] notesLabel = {new JLabel("Write your notes : ")};
        notesLabel[0].setFont(new Font("Arial", Font.PLAIN, 13));
        notesLabel[0].setBounds(12, 31, 75, 20);
        frame.getContentPane().add(notesLabel[0]);

        JTextArea textNotes = new JTextArea();
        textNotes.setFont(new Font("Arial", Font.PLAIN, 12));
        textNotes.setSize(190, 20);
        textNotes.setBounds(100, 28, 275, 40);
        textNotes.setColumns(10);
        frame.getContentPane().add(textNotes);

        JButton addImage  = new JButton("Add Image");
        addImage.setFont(new Font("Arial", Font.BOLD, 10));
        addImage.setBounds(160, 185, 100, 20);
        frame.getContentPane().add(addImage);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 12));
        submitButton.setSize(100, 20);
        submitButton.setBounds(250, 100, 100, 20);
        frame.getContentPane().add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                notes[0] = textNotes.getText();
            }
        });
        return notes[0];
    }


    private void scheduleConsultation(Patient patient, int doctorId, LocalDateTime bookingSlot, List<Doctor> doctors, String cost, String notes, WestminsterSkinConsultationManager manager) {
        boolean isAvailable;
        if (doctorId > 0) {
            isAvailable = manager.checkAvailability(doctorId, bookingSlot);
            if (isAvailable) {
                Doctor doctor = WestminsterSkinConsultationManager.getDoctors().get(doctorId);
                Consultation consultation = new Consultation(bookingSlot, doctor, patient, Integer.parseInt(cost), notes);
                manager.createConsultation(consultation, patient, doctor);
                JOptionPane.showMessageDialog(null, "The consultation has been scheduled successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "The doctor you looked for is not available on your booking slot.");
                while (true) {
                    Random ran = new Random();
                    int newDocId = ran.nextInt(doctors.size());
                    isAvailable = manager.checkAvailability(newDocId, bookingSlot);
                    if (isAvailable) {
                        Doctor doctor = WestminsterSkinConsultationManager.getDoctors().get(doctorId);
                        Consultation consultation = new Consultation(bookingSlot, doctor, patient, Integer.parseInt(cost), notes);
                        manager.createConsultation(consultation, patient, doctor);
                        break;
                    }
                }
            }
        }
    }
}
