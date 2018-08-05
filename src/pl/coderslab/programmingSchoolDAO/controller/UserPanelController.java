package pl.coderslab.programmingSchoolDAO.controller;


import pl.coderslab.programmingSchoolDAO.DAO.UserDAO;
import pl.coderslab.programmingSchoolDAO.entity.Solution;
import pl.coderslab.programmingSchoolDAO.DAO.SolutionDAO;

import java.util.List;
import java.util.Scanner;

import static pl.coderslab.programmingSchoolDAO.controller.MainController.getInt;

public class UserPanelController {
    static Scanner scann;
    public static void main(String[] args) throws Exception {
        int userID;
        try {
            userID = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new Exception("Wrong user id.");
        }
        if(UserDAO.loadUserById(userID) == null) throw new Exception("Enter valid id.");
        scann = new Scanner(System.in);
        userToolsOptions(userID);
    }


    public static void userToolsOptions(int userId){
        while (true) {
            System.out.println("What would you like to do: \n(1) add a solution)\n(2) view your solutions)\n(0) quit");
            switch (getInt(scann)){
                case 1:
                    addSolution(userId);
                    break;
                case 2:
                    viewSolutions(userId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Incorrect input - try again.");

            }
        }
    }


    public static void addSolution(int id){
        try{
      List<Solution> solutions = SolutionDAO.loadAllNotDone(id);
        for (Solution solution : solutions) {
            System.out.println(solution);
        }

        if (solutions.isEmpty()){
            userToolsOptions(id);
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        int taskId = getIntFromUser(Type.SOLUTION_ID);
        String description = getDescription();
        Solution solution = new Solution(description,taskId,id);
        try {
            SolutionDAO.saveToDb(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void viewSolutions(int id){
        try {
            for (Solution solution : SolutionDAO.loadAllByUserID(id)) {
                System.out.println(solution);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getDescription() {
        System.out.println("Enter solution: ");
        return scann.nextLine();
    }

    public static int getIntFromUser(Type type) {
        switch (type) {
            case USER_ID:
                System.out.println("Enter your id: ");
                break;
            case SOLUTION_ID:
                System.out.println("Enter your solution id: ");
                break;
        }
        while (!scann.hasNextInt()){
            scann.next();
            System.out.println("Wrong input - you have to enter a number");
        }
        int id = scann.nextInt();
        scann.nextLine();
        return id;
    }

    public enum Type{
        USER_ID,
        SOLUTION_ID,
    }
}
