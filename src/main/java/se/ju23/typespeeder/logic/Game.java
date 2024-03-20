package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Challenge;

import java.util.*;

@Component

public class Game implements GameInter {
    private Scanner scanner;
    private float score;

    @Autowired
    private GametaskRepo gtRepo;

    public Game() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void startChallenge(Gametask gametask) {
        Challenge challenge = new Challenge();
        challenge.startChallenge();
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

    public void lettersToType() {
        StringBuilder letters = new StringBuilder();
        Random random = new Random();
        int numLetters = 10;
        for (int i = 0; i < numLetters; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            letters.append(randomChar).append(" ");
        }
        System.out.println("Letters to type: " + letters);
    }

    private String getUserInput() {

        return scanner.nextLine();

    }

    private boolean validateInput(String wordFromDatabase, String userInput) {
        return wordFromDatabase.equals(userInput);
    }


    public double calculateSpeed(Gametask gametask) {
        long startTime = gametask.getStartTime();
        long endTime = gametask.getEndTime();

        long durationInMillis = endTime - startTime;
        double durationInSeconds = durationInMillis / 1000.0; // milliseconds to seconds

        String solution = gametask.getSolution();
        int wordCount = solution.split("\\s+").length; // number of words

        return wordCount / durationInSeconds; // words per second
    }

    public int calculateMostRights(Gametask gametask) {
        String userInput = gametask.getUserInput();
        String solution = gametask.getSolution();
        String[] userWords = userInput.split("\\s+");
        String[] solutionWords = solution.split("\\s+");
        int rights = 0;
        for (int i = 0; i < Math.min(userWords.length, solutionWords.length); i++) {
            if (userWords[i].equals(solutionWords[i])) {
                rights++;
            } else {
                break;
            }
        }
        return rights;
    }

    public String calculateMostRightsInOrder(Gametask gametask) {
        String userInput = gametask.getUserInput();
        String solution = gametask.getSolution();
        String[] userWords = userInput.split("\\s+");
        String[] solutionWords = solution.split("\\s+");
        StringBuilder mostRightsInOrder = new StringBuilder();
        for (int i = 0; i < Math.min(userWords.length, solutionWords.length); i++) {
            if (userWords[i].equals(solutionWords[i])) {
                mostRightsInOrder.append(userWords[i]).append(" ");
            } else {
                break;
            }
        }
        return mostRightsInOrder.toString().trim();
    }

    public double calculateAverage(double speed, int mostRights, String mostRightsInOrder) {
        // Calculate the average based on speed, most rights, and most rights in order
        // For example, you could use a weighted average
        return (speed + mostRights + mostRightsInOrder.length()) / 3.0;
    }


    public String getRandomSolution(){
        List<Gametask> allGameTasks = gtRepo.findAll();
        List<String> solutions = new ArrayList<>();
        for (Gametask task: allGameTasks) {
            solutions.add(task.getSolution());
        }
        Random randomPosition = new Random();
        return allGameTasks.get(randomPosition.nextInt(allGameTasks.size())).getSolution();
    }


}
