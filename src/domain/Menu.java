package domain;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void menuHandler() throws IOException {
        int choiceInput;

        System.out.println("****************************************************\n" +
                "Welcome. Please pick a number of one of the following options:\n" +
                "\n" +
                "1. Find a word(s)\n" +
                "2. Find words by definition\n" +
                "3. Find all words that start with -\n" +
                "4. Find all words that end with -\n" +
                "5. Find all words containing -\n" +
                "6. Add a word\n" +
                "7. Delete a word\n" +
                "8. History\n" +
                "9. Creator\n" +
                "10. Exit\n" +
                "****************************************************");
        Scanner scanner = new Scanner(System.in);

        try {
            choiceInput = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input. Please ensure your input is a number");
            return;
        }

        if (choiceInput == 1) {
            // find word(s)
        } else if (choiceInput == 2) {
            // find words by definition
            
        } else if (choiceInput == 3) {
            // Find all words that start with

        } else if (choiceInput == 4) {
            // Find all words that end with

        } else if (choiceInput == 5) {
            // Find all words containing

        } else if (choiceInput == 6) {
            // Add a word
            WordWriter wordWriter = new WordWriter();
            wordWriter.writeEntries();
        } else if (choiceInput == 7) {
            // Delete a word

        } else if (choiceInput == 8) {
            // History

        } else if (choiceInput == 9) {
            // Creator
            System.out.println("This dictionary app was created by Daniel Moreno.");

        } else if (choiceInput == 10) {
            // Exit
            System.out.println("Exiting application...");
            System.exit(0);

        } else {

        }
    }
}
