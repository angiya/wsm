package com.mycompany;

public class Main {

    static WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    public static void main(String[] args) {
        manager.loadData();
        System.out.println("Welcome to the Formula 1 Championship Manager!");
        while (true) {
            int selection = manager.selection();
            switch (selection) {
                case 1:
                    manager.addDoctor();
                    break;
                case 2:
                    manager.deleteDoctor();
                    break;
                case 3:
                    manager.displayDoctors();
                    break;
                case 4:
                    manager.saveData();
                    break;
                case 5:
                    manager.runGUI();
                    break;
                case 6:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Your selection is invalid.");
            }
        }
    }
}