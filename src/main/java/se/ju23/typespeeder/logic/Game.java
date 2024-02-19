package se.ju23.typespeeder.logic;

import se.ju23.typespeeder.logic.Gametask;

public class Game {

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
        return "User input from console";
    }

    private boolean validateInput(String wordFromDatabase, String userInput) {
        return wordFromDatabase.equals(userInput.trim());
    }

}
