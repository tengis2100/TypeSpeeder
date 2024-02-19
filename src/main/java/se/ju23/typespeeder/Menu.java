package se.ju23.typespeeder;

import se.ju23.typespeeder.db.UserDAO;
import se.ju23.typespeeder.db.UserEntity;
import se.ju23.typespeeder.logic.GametaskInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuService {

 UserEntity ue;

MenuService mu;

GametaskInterface gti;


        private UserDAO userDAO;

        public Menu() {
            this.userDAO = new UserDAO();
        }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startMenu();
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("Menu:");
            System.out.println("1. Create new user");
            System.out.println("2. Delete user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createUserFromMenu();
                    break;
                case 2:
                    deleteUserFromMenu();
                    break;
                case 3:
                    System.out.println("Exiting menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
        scanner.close();
    }

    private void createUserFromMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter game level: ");
        int gameLevel = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter game name: ");
        String gameName = scanner.nextLine();

        createUser(username, password, gameLevel, gameName);
    }

    private void deleteUserFromMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID to delete: ");
        long userId = scanner.nextLong();

        deleteUser(userId);
    }

        public void createUser(String username, String password, int gameLevel, String gameName) {
            boolean success = userDAO.createUser(username, password, gameLevel, gameName);
            if (success) {
                System.out.println("User created");
            } else {
                System.out.println("Failed to create user. Please try again.");
            }
        }

        public void deleteUser(long userId) {
            boolean success = userDAO.deleteUser(userId);
            if (success) {
                System.out.println("User deleted");
            } else {
                System.out.println("Failed to delete user. Please try again.");
            }
        }

    @Override
    public List<String> getMenuOptions() {
        return null;
    }

    @Override
    public void displayMenu() {

    }
}



//    @Override
//    public List<String> getMenuOptions() {
//        List<String> options = new ArrayList<>();
//        options.add("Option 1");
//        options.add("Option 2");
//        options.add("Option 3");
//        options.add("Option 4");
//        options.add("Option 5");
//        return options;
//    }
//
//    @Override
//    public void displayMenu() {
//        List<String> options = getMenuOptions();
//        System.out.println("Menu:");
//        for (int i = 0; i < options.size(); i++) {
//            System.out.println((i + 1) + ". " + options.get(i));
//        }
//    }

