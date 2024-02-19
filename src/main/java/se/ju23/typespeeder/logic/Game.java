package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.logic.Gametask;

import java.util.Scanner;

public class Game {
        private Scanner scanner;

        public Game() {
            scanner = new Scanner(System.in);
        }

        public void startGame(Gametask gametask) {
            String wordFromDatabase = gametask.getSolution();

            System.out.println("Type the following word:");
            System.out.println(wordFromDatabase);

            System.out.println("Type the word and press enter:");

            String userInput = getUserInput();

            boolean isCorrect = validateInput(wordFromDatabase, userInput);

            if (isCorrect) {
                System.out.println("Congratulations");
            } else {
                System.out.println("Sorry, Try again.");
            }
        }

        private String getUserInput() {
            return scanner.nextLine().trim(); // Read user input from console and trim leading/trailing spaces
        }

        private boolean validateInput(String wordFromDatabase, String userInput) {
            return wordFromDatabase.equals(userInput);
        }

        // Methods to handle timestamps


        public double calculateSpeed(Gametask gametask) {
            long startTime = gametask.getStartTime();
            long endTime = gametask.getEndTime();

            long durationInMillis = endTime - startTime;
            double durationInSeconds = durationInMillis / 1000.0; // Convert milliseconds to seconds

            String solution = gametask.getSolution();
            int wordCount = solution.split("\\s+").length; // Count the number of words

            return wordCount / durationInSeconds; // Speed is measured in words per second
        }

        public int calculateMostRights(Gametask gametask) {
        // Determine the highest number of correct guesses before failing
        String userInput = gametask.getUserInput();
        String solution = gametask.getSolution();
        String[] userWords = userInput.split("\\s+");
        String[] solutionWords = solution.split("\\s+");
        int rights = 0;
        for (int i = 0; i < Math.min(userWords.length, solutionWords.length); i++) {
            if (userWords[i].equals(solutionWords[i])) {
                rights++;
            } else {
                break; // Stop counting if the user makes a mistake
            }
        }
        return rights;
    }

    public String calculateMostRightsInOrder(Gametask gametask) {
        // Determine the highest number of correct guesses in order
        String userInput = gametask.getUserInput();
        String solution = gametask.getSolution();
        String[] userWords = userInput.split("\\s+");
        String[] solutionWords = solution.split("\\s+");
        StringBuilder mostRightsInOrder = new StringBuilder();
        for (int i = 0; i < Math.min(userWords.length, solutionWords.length); i++) {
            if (userWords[i].equals(solutionWords[i])) {
                mostRightsInOrder.append(userWords[i]).append(" ");
            } else {
                break; // Stop appending if the user makes a mistake
            }
        }
        return mostRightsInOrder.toString().trim();
    }

    public double calculateAverage(double speed, int mostRights, String mostRightsInOrder) {
        // Calculate the average based on speed, most rights, and most rights in order
        // For example, you could use a weighted average
        return (speed + mostRights + mostRightsInOrder.length()) / 3.0;
    }


}
