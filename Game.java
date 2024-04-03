package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Game{
   
    private int secretNumber;
    private int maxGuesses;
    private int score;
    private int attempts;
    private Scanner scanner;
    private static final int MAX_NUMBER = 100;
    private String name;
     {
    	 scanner = new Scanner(System.in);
    	System.out.println("enter your name");
    	name = scanner.next();
    	System.out.println("Hello "+name+"! Welcome to the Guess the Number game.");
        System.out.println("You have three difficulty levels in this game:");
        System.out.println("1. Easy: You get unlimited chances to guess the number.");
        System.out.println("2. Medium: You get 5 chances to guess the number.");
        System.out.println("3. Hard: You get 2 chances to guess the number.");
        System.out.println("Please enter the desired difficulty level (Easy/Medium/Hard):");
    }
    public Game() {
        String level = scanner.next().toUpperCase();

        switch (level) {
            case "EASY":
                maxGuesses = Integer.MAX_VALUE;
                break;
            case "MEDIUM":
                maxGuesses = 5;
                break;
            case "HARD":
                maxGuesses = 2;
                break;
            default:
                System.err.println("Invalid difficulty level! Please choose Easy, Medium, or Hard.");
                System.exit(1);
        }

        secretNumber = generateSecretNumber();
        score = 100;
        attempts = 0;
    }

    public void start() {
        while (attempts < maxGuesses) {
            System.out.println("Enter a number within the range of 1 to 100:");
            int userGuess = getUserInput();
            attempts++;
            if (userGuess == secretNumber) {
                System.out.println("Congratulations!"+name+" You won the game!");
                break;
            } else if (userGuess < secretNumber) {
                System.out.println("Oops! Your guess is too low.");
            } else {
                System.out.println("Oops! Your guess is too high.");
            }
            score -= 5;
        }

        if (attempts >= maxGuesses) {
            System.err.println("hey " +name+" You lost the game! The secret number was: " + secretNumber+" better luck next time");
            score = 0;
        }

        System.out.println("Your final score: " + score);
        scanner.close();
    }

    private int generateSecretNumber() {
        return new Random().nextInt(MAX_NUMBER) + 1;
    }

    private int getUserInput() {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                if (input < 1 || input > MAX_NUMBER) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input! Please enter a number within the range of 1 to 100.");
                scanner.nextLine();
            }
        }
        return input;
    }
    
}