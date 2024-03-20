package se.ju23.typespeeder;

import java.util.Random;

public class Challenge {

    public void startChallenge() {
        System.out.println("Challenge start");
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
}
