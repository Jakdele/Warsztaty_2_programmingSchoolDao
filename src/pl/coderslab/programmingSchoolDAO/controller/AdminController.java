package pl.coderslab.programmingSchoolDAO.controller;

import pl.coderslab.programmingSchoolDAO.service.DBManager;

import java.util.Scanner;

import static pl.coderslab.programmingSchoolDAO.controller.MainController.getInt;

public class AdminController {
    static Scanner scanner;

    public static void adminPanel() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What module do you want to work on?\n(1) user\n(2) group\n(3) exercise\n(4) solution\n(0) quit");
            switch (getInt(scanner)){
                case 1:
                    UserController.userOptions();
                    break;
                case 2:
                    GroupController.groupOptions();
                    break;
                case 3:
                    ExerciseController.exerciseOptions();
                    break;
                case 4:
                    SolutionController.solutionOptions();
                    break;
                case 0:
                    closeApp();
                    break;
                default:
                    System.out.println("Incorrect input - try again.");
            }
        }
    }


    public static boolean getUserConfirmation(){
        while(true) {
            System.out.println("Are you sure you want to delete this record \ny - yes\nn - no");
            String answer = scanner.nextLine();
            switch (answer) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Enter 'y' to confirm, 'n' to cancel.");
            }
        }

    }
    public static void closeApp(){
        DBManager.close();
        System.out.println("Application closed");
        System.exit(0);
    }


}


