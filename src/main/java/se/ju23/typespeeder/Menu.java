package se.ju23.typespeeder;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.db.LeaderboardDAO;

import se.ju23.typespeeder.db.UserDAO;
import se.ju23.typespeeder.db.UserEntity;
import se.ju23.typespeeder.logic.Game;
import se.ju23.typespeeder.logic.Gametask;
import se.ju23.typespeeder.logic.GametaskInterface;
import se.ju23.typespeeder.logic.GametaskDAO;

import se.ju23.typespeeder.db.Leaderboard;


import java.util.*;
import java.util.concurrent.TimeUnit;

@Component

public class Menu implements MenuService {

    private GametaskDAO gametaskDAO;
    private UserDAO userDAO;
    private Scanner scanner;
    private List<String> options = new ArrayList<>();


    public Menu(Scanner scanner) {
        this.userDAO = new UserDAO();
        this.gametaskDAO = new GametaskDAO();
        this.scanner = scanner;
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");
        options.add("Option 4");
        options.add("Option 5");
    }

    public Menu() {
        this.userDAO = new UserDAO();
        this.gametaskDAO = new GametaskDAO();
        this.scanner = new Scanner(System.in);
        options.add("Option 1");
        options.add("Option 2");
        options.add("Option 3");
        options.add("Option 4");
        options.add("Option 5");
    }
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startMenu();
    }

    public void startMenu() {
        System.out.println("Välj språk (svenska/engelska):");

        String languageChoice = scanner.nextLine().trim().toLowerCase();

        switch (languageChoice) {
            case "engelska":
                displayMenu();
                break;
            case "svenska":
                displayMenu();
                System.out.println("Svenska valt.");
                break;
            default:
                System.out.println("Ogiltigt val. Standardinställning till engelska.");
                displayMenu();
        }
    }
    @Override

    public <T extends Enum<T> & Messagable>void displayMenu( Class <T> menu){
        T[] list = menu.getEnumConstants();

        for (int i = 0; i < list.length; i++) {
            System.out.println(i+1 + ". " + list[i].getMassage());
        }
        System.out.println();

    }

    public <T extends Messagable> T getUserChoise(T[] options){
        int choise = 0;
        do {
            System.out.println("Choose an option: ");
            try {
                choise = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong input , try again");
                scanner.nextLine();
                continue;
            }
        }while(choise > options.length || choise < 1);
        return options[choise-1];
    }


    public void userMenu(String language) {
        System.out.println("Meny (" + language + "):");
        System.out.println("1. Skapa ny användare");
        System.out.println("2. Ta bort användare");
        System.out.println("3. Avsluta");
        System.out.print("Ange ditt val: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                createUserFromMenu();
                break;
            case 2:
                deleteUserFromMenu();
                break;
            case 3:
                System.out.println("Avslutar meny...");
                break;
            default:
                System.out.println("Ogiltigt val. Ange ett nummer mellan 1 och 3.");
        }
    }





//    public void playGameAndCalculateLeaderboard(UserEntity user) {
//        Game game = new Game();
//
//        Gametask gametask = getGametaskFromDatabase();
//
//        game.startChallenge(gametask);
//
//        long startTime = gametask.getStartTime();
//        long endTime = gametask.getEndTime();
//        String userInput = gametask.getUserInput();
//        int totalWordsTyped = userInput.split("\\s+").length;
//
//        double speed = calculateSpeed(gametask, startTime, endTime, totalWordsTyped);
//        int mostRights = calculateMostRights(gametask, userInput);
//        String mostRightsInOrder = calculateMostRightsInOrder(gametask);
//        double average = calculateAverage(speed, mostRights, mostRightsInOrder);
//
//        updateLeaderboard(user.getUserid(), speed, mostRights, mostRightsInOrder, average);
//    }

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
        return (speed + mostRights + mostRightsInOrder.length()) / 3; // Example
    }

    private void updateLeaderboard(long userId, double speed, int mostRights, String mostRightsInOrder, double average) {

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setPlayerid(userId);
        leaderboard.setSpeed(speed);
        leaderboard.setMostrights(mostRights);
        leaderboard.setMostrightInorder(mostRightsInOrder);
        leaderboard.setAverage(average);
    }


    public void createUserFromMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter game level: ");
        int gameLevel = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter game name: ");
        String gameName = scanner.nextLine();

        createUser(username, password, gameLevel, gameName);
    }

    public void deleteUserFromMenu() {
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
        System.out.println("vilken id vill du ta bort");
        boolean success = userDAO.deleteUser(userId);
        if (success) {
            System.out.println("User deleted");
        } else {
            System.out.println("Failed to delete user. Please try again.");
        }
    }

    @Override
    public List<String> getMenuOptions() {
        return options;
    }


    public void displayMenu() {
        List<String> options = getMenuOptions();
        System.out.println("Menu:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }
}
