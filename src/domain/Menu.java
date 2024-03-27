package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public void enterToContinue() throws IOException {
        System.out.println("Press 'Enter' to see the main menu. Type 'exit' and hit enter to exit the application.");
        String continueChoice = scanner.nextLine();

        if (continueChoice.equalsIgnoreCase("exit")) {
            System.out.println("Exiting application...");
            System.exit(0);
        } else if (continueChoice.trim().isEmpty()) {
            displayMenu();
        }


    }

    public void displayMenu() throws IOException {
        DictionaryManager dictionaryManager = new DictionaryManager();

        boolean continueLoop = true;

        while (continueLoop) {
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

            int choiceInput;
            try {
                choiceInput = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please ensure your input is a number");
                continue; // starts from beginning
            }

            switch (choiceInput) {
                case 1: // Find word
                    System.out.println("Please enter the word(s) you would like to find. If more than one, please separate them by commas.");
                    String wordInput = scanner.nextLine().trim(); // ask for a word
                    String[] wordSearch = wordInput.split(","); // turn search into a list of Strings
                    dictionaryManager.findWord(wordSearch); // take a word as a parameter
                    break;
                case 2: // Find definitions
                    System.out.println("Please enter word(s)/phrase(s) you would like to find in word definitions. If more than one, please separate them by commas.");
                    String definitionInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
                    String[] definitionSearch = definitionInput.split(",");; // creating an array to hold the words in the search
                    dictionaryManager.findDefinition(definitionSearch);
                    break;
                case 3: // Starts with...
                    System.out.println("Find words that start with (separate multiple by commas): ");
                    String prefixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
                    String[] prefixSearch = prefixInput.split(",");
                    dictionaryManager.findStartsWith(prefixSearch);
                    break;
                case 4: // Ends with...
                    System.out.println("Find words that end with (separate multiple by commas): ");
                    String suffixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
                    String[] suffixSearch = suffixInput.split(",");
                    dictionaryManager.findEndsWith(suffixSearch);
                    break;
                case 5: // Contains...
                    System.out.println("Please enter the substring(s) you would like to find in a word. If more than one, please separate them by commas.");
                    String substringInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
                    String[] substringSearch = substringInput.split(",");; // creating an array to hold the words in the search
                    dictionaryManager.findWordsContaining(substringSearch);
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
                    break;
                case 10: // Exit
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Error: Invalid input. Please try again." + "\n");
                    break;
            }
            System.out.println("Press 'Enter' to see the main menu. Type 'exit' and hit enter to exit the application.");
            String continueChoice = scanner.nextLine();

            if (continueChoice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application...");
                System.exit(0);
            } else if (continueChoice.trim().isEmpty()) {
                continueLoop = true;
            } else {
                System.out.println("Invalid entry. Please try again");
                continueLoop = true;
            }
        }
    }

    // ============== PRINT MATCHING ENTRIES =================
    private static String noMatches = "No matching words found." + "\n";
    private static String yesMatches = "Matching entries:";
    public static String printMatchingEntries(List<Word> matchingEntries) {
        if (matchingEntries.isEmpty()) { // checks if matchingEntries is empty
            return noMatches;
        } else {
            String foundMatches = yesMatches;
            for (Word entry : matchingEntries) {
                foundMatches = foundMatches + "\n" + entry;
            }
            return foundMatches;
        }
    }

    // ============== ADD WORD MENU =================
    static String wordInput;
    static String definitionInput;
    static String partOfSpeechInput;
    static String exampleInput;


    public static String promptNewWord() {
        while (true) {
            System.out.println("Please enter a word to add to the dictionary:");
            wordInput = scanner.nextLine().trim();
            if (Utilities.isValidWord(wordInput)) { // if returns true the word is valid
                System.out.println("New word to be added: " + wordInput);
                break; // Exit the loop if the word is valid
            } else {
                System.out.println("Invalid word. Please enter a word containing only letters.");
            }
        }
        return wordInput;
    }

    public static String promptNewDefinition() {
        while (true) {
            System.out.println("Please enter the definition of the provided word: ");
            definitionInput = scanner.nextLine().trim();
            if (Utilities.isValidDefinitionOrExample(definitionInput)) { // if returns true the word is valid
                System.out.println("Definition for " + wordInput + ": " + definitionInput);
                break; // Exit the loop if the definition is valid
            } else {
                System.out.println("Invalid definition. Please enter a definition that is more than two words.");
            }
        }
        return definitionInput;
    }

    public static String promptNewPartOfSpeech() {
        do {
            System.out.println("""
                        Please enter the number corresponding of the part of speech of the word:
                        1. noun - the name of a person, place, thing, or idea.
                        2. verb - expresses action or being.
                        3. adjective - modifies or describes a noun or pronoun.
                        4. adverb - modifies or describes a verb, an adjective, or another adverb.
                        5. pronoun - a word used in place of a noun.
                        6. preposition - a word placed before a noun or pronoun to form a phrase modifying another word in the sentence.
                        7. conjunction - joins words, phrases, or clauses.
                        8. interjection - a word used to express emotion.
                        """);
            partOfSpeechInput = Utilities.convertPartOfSpeechMenuChoice(scanner.nextLine().trim());
        } while (partOfSpeechInput.equals("invalid"));
        System.out.println("Part of speech chosen: " + partOfSpeechInput);
        return partOfSpeechInput;
    }

    public static String promptNewExample() {
        // ask for an example
        while (true) {
            System.out.println("Please enter a sentence using the word: ");
            exampleInput = scanner.nextLine().trim();
            if (Utilities.isValidDefinitionOrExample(exampleInput)) { // if returns true the word is valid
                System.out.println("Example usage of " + wordInput + ": " + exampleInput);
                break; // Exit the loop if the definition is valid
            } else {
                System.out.println("Invalid example. Please enter a sentence that is more than two words.");
            }
        }
        return exampleInput;
    }

    public static boolean confirmNewWord(String wordInput, String definitionInput, String partOfSpeechInput, String exampleInput) {
        System.out.println("========== NEW DICTIONARY ENTRY ==========\n" +
                "Word: " + wordInput + "\n" +
                "Definition: " + definitionInput + "\n" +
                "Part of speech: " + partOfSpeechInput + "\n" +
                "Example usage: " + exampleInput + "\n\n" +
                "**Press 'Enter' to confirm. Type 'exit' and press 'Enter' to start over.**");

        String confirmEntry = scanner.nextLine();
        return !Utilities.newEntryConfirmation(confirmEntry);


    }

}
