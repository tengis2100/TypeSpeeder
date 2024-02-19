package se.ju23.typespeeder;

import se.ju23.typespeeder.db.LeaderboardDAO;

import se.ju23.typespeeder.db.UserDAO;
import se.ju23.typespeeder.db.UserEntity;
import se.ju23.typespeeder.logic.Game;
import se.ju23.typespeeder.logic.Gametask;
import se.ju23.typespeeder.logic.GametaskInterface;
import se.ju23.typespeeder.logic.GametaskDAO;

import se.ju23.typespeeder.db.Leaderboard;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu implements MenuService {

 UserEntity ue;

MenuService mu;

GametaskInterface gti;

LeaderboardDAO lb;

LeaderboardDAO ldb;

        private GametaskDAO gametaskDAO;
        private UserDAO userDAO;

        public Menu() {
            this.userDAO = new UserDAO();
            this.gametaskDAO = new GametaskDAO();
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


    public void playGameAndCalculateLeaderboard(UserEntity user) {
        Game game = new Game();

        Gametask gametask = getGametaskFromDatabase();

        game.startChallenge(gametask);

        long startTime = gametask.getStartTime();
        long endTime = gametask.getEndTime();
        String userInput = gametask.getUserInput();
        int totalWordsTyped = userInput.split("\\s+").length;

        double speed = calculateSpeed(gametask, startTime, endTime, totalWordsTyped);
        int mostRights = calculateMostRights(gametask, userInput);
        String mostRightsInOrder = calculateMostRightsInOrder(gametask);
        double average = calculateAverage(speed, mostRights, mostRightsInOrder);

        updateLeaderboard(user.getUserid(), speed, mostRights, mostRightsInOrder, average);
    }

    private Gametask getGametaskFromDatabase() {
        return gametaskDAO.getGametask();
    }

    private double calculateSpeed(Gametask gametask, long startTime, long endTime, int totalWordsTyped) {
        long timeTakenInMillis = endTime - startTime;

        double timeTakenInMinutes = TimeUnit.MILLISECONDS.toMinutes(timeTakenInMillis);

        return totalWordsTyped / timeTakenInMinutes;
    }

    private int calculateMostRights(Gametask gametask, String userInput) {
        String[] correctWords = gametask.getSolution().split("\\s+");
        String[] typedWords = userInput.split("\\s+");

        int correctWordsCount = 0;

        // Count the number of correct words typed by the user
        for (int i = 0; i < correctWords.length && i < typedWords.length; i++) {
            if (correctWords[i].equals(typedWords[i])) {
                correctWordsCount++;
            } else {
                break; // Break the loop as soon as a word is mistyped
            }
        }

        return correctWordsCount;
    }

        private String calculateMostRightsInOrder(Gametask gametask) {
            return "1";
        }

        private double calculateAverage(double speed, int mostRights, String mostRightsInOrder) {
            return (speed + mostRights + mostRightsInOrder.length()) / 3; // Example calculation
        }

        private void updateLeaderboard(long userId, double speed, int mostRights, String mostRightsInOrder, double average) {

            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setPlayerid(userId);
            leaderboard.setSpeed(speed);
            leaderboard.setMostrights(mostRights);
            leaderboard.setMostrightInorder(mostRightsInOrder);
            leaderboard.setAverage(average);
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

