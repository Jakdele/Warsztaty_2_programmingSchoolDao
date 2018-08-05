package pl.coderslab.programmingSchoolDAO.controller;

import pl.coderslab.programmingSchool.admin.MainController;

import pl.coderslab.programmingSchoolDAO.DAO.ExerciseDAO;
import pl.coderslab.programmingSchoolDAO.entity.Exercise;


import java.util.List;

import static pl.coderslab.programmingSchoolDAO.controller.AdminController.closeApp;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.getInt;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.scanner;

public class ExerciseController {
    static void exerciseOptions() {
        while (true) {
            printAllExercises();
            System.out.println("What would you like to do: \n(1) edit an existing exercise\n(2) add a new exercise\n(3) delete an exercise\n(0) quit the app");
            switch (getInt(scanner)) {
                case 1:
                    editExercise();
                    break;
                case 2:
                    addExercise();
                    break;
                case 3:
                    deleteExercise();
                    break;
                case 0:
                    closeApp();
                    break;
                default:
                    System.out.println("Incorrect input - try again.");
            }
        }
    }

    public static void editExercise() {
        Integer id = getID();
        String title = getStringFromUser(Type.TITLE);
        String description = getStringFromUser(Type.DESC);
        Exercise newExercise = new Exercise(id, title, description);
        try {
            ExerciseDAO.saveToDb(newExercise);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addExercise() {
        String title = getStringFromUser(Type.TITLE);
        String description = getStringFromUser(Type.DESC);
        Exercise newExercise = new Exercise(title, description);
        try {
            ExerciseDAO.saveToDb(newExercise);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteExercise() {
        int id = getID();
        if (MainController.getUserConfirmation() == true && id != 0)
            ExerciseDAO.delete(id);
    }

    public static void printAllExercises() {
        System.out.println("All exercises list:");
        try {
            List<Exercise> allExercises = ExerciseDAO.findAll();
            for (Exercise exercise : allExercises) {
                System.out.println(exercise);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getStringFromUser(Type type) {
        switch (type) {
            case TITLE:
                System.out.println("Enter new exercise title:");
                break;
            case DESC:
                System.out.println("Enter new exercise description:");
                break;

            default:
                break;
        }
        return scanner.nextLine();
    }

    public static int getID() {
        System.out.println("Enter exercise id: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Id has to be a number. \nEnter id: ");
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private enum Type {
        TITLE,
        DESC
    }
}
