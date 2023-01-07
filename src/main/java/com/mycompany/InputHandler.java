package com.mycompany;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

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

    public String enterText(String nameType) {
        while (true) {
            System.out.println("Please enter the doctor's " + nameType + " : ");
            String text = scanner.nextLine();
            System.out.println("Please enter the doctor's surname");
            if (text.matches("[a-zA-Z_]+")) return text;
            System.out.println("Please enter a valid " + nameType + ".");
        }
    }

    public LocalDate enterDateOfBirth() {
        while (true) {
            try {
                System.out.println("Please enter the doctor's date of birth : (dd/mm/yyyy)");
                String dateOfBirth = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(dateOfBirth, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date");
            }
        }
    }

    public String enterNumber(String numberType) {
        while (true) {
            System.out.println("Please enter the doctor's " + numberType + " Number");
            String mobileNumber = scanner.nextLine();
            if(mobileNumber.matches("[0-9]+")) return mobileNumber;
            System.out.println("Please enter a valid mobile number");
        }
    }
}

// TODO Please enter the doctor's surname
