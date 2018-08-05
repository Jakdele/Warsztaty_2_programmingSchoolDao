package pl.coderslab.programmingSchoolDAO.controller;
import pl.coderslab.programmingSchool.user.UserTools;

import java.util.Scanner;

public class MainController {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
            String [] paramateres = new String[1];
            while (true) {
                System.out.println("Welcome to programming school app");
                System.out.println("This app allows you to manage your users, user groups, exercises and solutions for your student.\n" +
                        "If you are a student, you can browse and update your solutions for exercises.");
                System.out.println("Work:\n(1) as admin\n(2) as user\n(0) quit the app");

                switch (getInt(scanner)){
                    case 1:
                       adminPanel();
                        break;
                    case 2:
                        try {
                            System.out.println("Enter your id: ");
                            paramateres[0] = String.valueOf(getInt(scanner));
                            UserTools.main(paramateres);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Incorrect input - try again.");
                }
            }
        }
        public  static void adminPanel() {
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
                        break;
                    default:
                        System.out.println("Incorrect input - try again.");
                }
            }

        }
    public static int getInt(Scanner scann){
        while (!scann.hasNextInt()){
            scann.next();
            System.out.println("Wrong input - you have to enter a number");
        }
        int id = scann.nextInt();
        scann.nextLine();
        return id;
    }


}


