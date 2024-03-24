package domain;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public void enterToContinue() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Press 'Enter' to see the main menu. Type 'exit' and hit enter to exit the application.");
            String continueChoice = scanner.nextLine();

            if (continueChoice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application...");
                System.exit(0);
                continueLoop = false;
            } else if (continueChoice.trim().isEmpty()) {
                displayMenu();
                continueLoop = false;
            } else {
                System.out.println("Invalid Entry. Please try again.");
            }
        }

    }

    public void displayMenu() {
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
    }

    public void menuHandler() throws IOException {
        Scanner scanner = new Scanner(System.in);
        DictionaryManager dictionaryManager = new DictionaryManager();

        while (true) {
            int choiceInput;
            try {
                choiceInput = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please ensure your input is a number");
                return;
            }

            switch (choiceInput) {
                case 1: // Find word
                    dictionaryManager.findWord();
                    break;
                case 2: // Find definitions
                    dictionaryManager.findDefinition();
                    break;
                case 3: // Starts with...
                    dictionaryManager.findStartsWith();
                    break;
                case 4: // Ends with...
                    dictionaryManager.findEndsWith();
                    break;
                case 5: // Contains...
                    dictionaryManager.findWordsContaining();
                    break;
                case 6: // Add word
                    dictionaryManager.addWord();
                    break;
                case 7: // Delete word
                    dictionaryManager.deleteWord();
                    break;
                case 8: // History
                    dictionaryManager.printHistory();
                    break;
                case 9: // Creator
                    dictionaryManager.printCreator();
                    // break;
                case 10: // Exit
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Error: Invalid input. Please try again." + "\n");
                    break;
            }
            enterToContinue();
        }
    }
}
