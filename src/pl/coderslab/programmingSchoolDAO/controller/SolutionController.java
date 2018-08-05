package pl.coderslab.programmingSchoolDAO.controller;

import pl.coderslab.programmingSchoolDAO.DAO.ExerciseDAO;
import pl.coderslab.programmingSchoolDAO.DAO.SolutionDAO;
import pl.coderslab.programmingSchoolDAO.entity.Solution;

import java.util.List;

import static pl.coderslab.programmingSchoolDAO.controller.AdminController.closeApp;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.getInt;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.scanner;

public class SolutionController {
    static void solutionOptions() {
        while (true) {
            System.out.println("All solutions list:");
            printAllSolutions();
            System.out.println("What would you like to do: \n(1) add (add new solution for a user)\n(2) view  solutions for a user with specified id\n(0) quit the app");
            switch (getInt(scanner)) {
                case 1:
                    addSolution();
                    break;
                case 2:
                    viewUser();
                    break;
                case 0:
                    closeApp();
                    break;
                default:
                    System.out.println("Incorrect input - try again.");

            }
        }
    }

    public static void addSolution() {
        try {
            UserController.printAllUsers();
            int userId = getIntFromUser(Type.USER_ID);
            ExerciseDAO.loadExerciseById(userId);
            int exerciseId = getIntFromUser(Type.EXERCISE_ID);
            Solution solution = new Solution(exerciseId, userId);
            SolutionDAO.saveToDb(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void viewUser() {
        UserController.printAllUsers();
        int id = getIntFromUser(Type.USER_ID);
        try {
            List<Solution> allSolutions = SolutionDAO.loadAllByUserID(id);
            if (allSolutions == null) {
                System.out.println("This user has no solutions assigned to his id.");
            } else {
                for (Solution solution : allSolutions) {
                    System.out.println(solution);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAllSolutions() {
        try {
            List<Solution> allSolutions = SolutionDAO.findAll();
            for (Solution solution : allSolutions) {
                System.out.println(solution);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIntFromUser(Type type) {
        switch (type) {
            case USER_ID:
                System.out.println("Enter user id:");
                break;
            case EXERCISE_ID:
                System.out.println("Enter exercise id:");
                break;
        }
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Wrong input - you have to enter a number");
        }
        return scanner.nextInt();
    }

    private enum Type {
        USER_ID,
        EXERCISE_ID
    }


}
