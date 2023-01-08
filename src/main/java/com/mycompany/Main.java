package com.mycompany;

public class Main {

    static final WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
    static final InputHandler inputHandler = new InputHandler();

    public static void main(String[] args) {
        manager.loadData();
        System.out.println("Welcome to the Formula 1 Championship Manager!");
        while (true) {
            int selection = inputHandler.selection();
            switch (selection) {
                case 1:
                    inputHandler.handleAddDoctor();
                    break;
                case 2:
                    inputHandler.handleDeleteDoctor();
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