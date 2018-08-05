package pl.coderslab.programmingSchoolDAO.controller;
import pl.coderslab.programmingSchoolDAO.entity.User;
import pl.coderslab.programmingSchoolDAO.DAO.UserDAO;

import java.util.List;

import static pl.coderslab.programmingSchoolDAO.controller.AdminController.closeApp;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.getInt;
import static pl.coderslab.programmingSchoolDAO.controller.MainController.scanner;

public class UserController {
    static void userOptions() {
        while (true) {
            printAllUsers();
            System.out.println("What would you like to do: \n(1) add a new user\n(2) edit user\n(3) delete a user\n(0) quit the app");
            switch (getInt(scanner)) {
                case 1:
                    addUser();
                    break;
                case 2:
                    editUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 0:
                    closeApp();
                    break;
                default:
                    System.out.println("Incorrect input - try again.");
            }
        }
    }

    public static void editUser() {
        Integer id= getIntFromUser(Type.USER_ID);
        String username = getStringFromUser(Type.NAME);
        String email = getStringFromUser(Type.EMAIL);
        String password = getStringFromUser(Type.PASS);
        Integer userGroupId= getIntFromUser(Type.GROUP_ID);

        User newUser = new User(id, username, email,password, userGroupId);
        try {
            UserDAO.saveToDb(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser() {
        String username = getStringFromUser(Type.NAME);
        String email = getStringFromUser(Type.EMAIL);
        String password = getStringFromUser(Type.PASS);
        int userGroupId= getIntFromUser(Type.GROUP_ID);

        User newUser = new User(username, email,password, userGroupId);
        try {
            UserDAO.saveToDb(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser() {
        int id = getIntFromUser(Type.USER_ID);
        if(AdminController.getUserConfirmation() == true && id != 0)
            UserDAO.delete(id);
    }
    public static void printAllUsers(){
        try {
            List<User> allUsers= UserDAO.findAll();
            for(User user: allUsers){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getStringFromUser (Type type){
        switch (type) {
            case NAME:
                System.out.println("Enter new username:");
                break;
            case EMAIL:
                System.out.println("Enter new email:");
                break;
            case PASS:
                System.out.println("Enter new password:");
                break;
            default:
                break;
        }
        return scanner.nextLine();
    }
    private static int getIntFromUser(Type type) {
        switch (type) {
            case USER_ID:
                System.out.println("Enter id of user you want to edit:");
                break;
            case GROUP_ID:
                System.out.println("Enter id of the group user belongs to:");
                break;
        }
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Wrong input - you have to enter a number");
        }
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private enum Type{
        NAME,
        PASS,
        EMAIL,
        USER_ID,
        GROUP_ID
    }
}
