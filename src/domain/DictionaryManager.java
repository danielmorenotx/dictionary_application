package domain;

import interfaces.WordReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class DictionaryManager {
    private ArrayList<ArrayList<Object>> historyList = new ArrayList<>();
    private static String noMatches = "No matching words found." + "\n";
    private static String yesMatches = "Matching entries";

    // ======== METHOD TO STORE CONTENT OF DICTIONARY IN AN ARRAY =============
    public ArrayList<Word> loadDictionary() throws IOException { // returns list of word objects

        // First checks if the dictionary exists
        File dictionaryFile = new File("./lib/dictionary.txt");
        if (!dictionaryFile.exists()) {
            try {
                if (dictionaryFile.createNewFile()) {
                    System.out.println("Successfully created a new dictionary" + "\n");
                } else {
                    System.out.println("Could not create a new dictionary :(" + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }

        // Load dictionary from the file
        WordReader wordReader = new domain.WordReader();
        String fullDictionary = wordReader.readEntries("./lib/dictionary.txt"); // took what the method returned and assigned to a String variable

        // splitting the string and turning it into an array
        // each line is an element in the array
        String[] dictionaryEntries = fullDictionary.split("\\n");

        ArrayList<Word> words = Arrays.stream(dictionaryEntries)
                .map(entry -> entry.split(" \\| "))
                .map(splitEntry -> new Word(splitEntry[0], splitEntry[1], splitEntry[2], splitEntry[3]))
                .collect(Collectors.toCollection(ArrayList::new));

        return words;
    }

    // ============ METHOD TO SAVE DICTIONARY WITH CHANGES ==============
    public void saveDictionary(ArrayList<Word> dictionaryEntries) throws IOException {
        // PrintWriter object to clear the text file
        PrintWriter writer = new PrintWriter("./lib/dictionary.txt");
        writer.print("");
        writer.close();

        WordWriter wordWriter = new WordWriter();
        wordWriter.writeEntries(dictionaryEntries);
    }


    // ============ 1. FIND WORDS ===========
    public void findWord() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word(s) you would like to find. If more than one, please separate them by commas.");
        String wordInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] wordSearch = wordInput.split(","); // turn search into a list of Strings
        logHistory.add("Find word(s): " + Arrays.toString(wordSearch));

        // Load dictionary entries
        List<Word> dictionaryEntries = loadDictionary();

        // Search every dictionary entry for the words  in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries

        for (Word entry : dictionaryEntries) {
            String word = entry.getWord(); // finds the word
            for (String searchedWord : wordSearch) {
                if (searchedWord.trim().equalsIgnoreCase(word)) {
                    matchingEntries.add(entry);
                }
            }
        }

        // Print matching entries
        if (matchingEntries.isEmpty()) {
            System.out.println(noMatches);
            logHistory.add(noMatches);
        } else {
            System.out.println(yesMatches);
            logHistory.add(yesMatches);
            for (Word entry : matchingEntries) {
                System.out.println(entry);
                logHistory.add(entry);
            }
        }
        System.out.println();

        // add to history list
        addToHistoryList(logHistory);
    }

    // ============ 2. FIND DEFINITIONS ===========
    public void findDefinition() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter word(s) you would like to find in definitions. If more than one, please separate them by commas.");
        String definitionInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] definitionSearch = definitionInput.split(",");; // creating an array to hold the words in the search
        logHistory.add("Search definitions for: " + Arrays.toString(definitionSearch));

        // Load dictionary entries
        List<Word> dictionaryEntries = loadDictionary();

        // Search every dictionary entry definition for the words in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries

        for (Word entry : dictionaryEntries) { // for every line in my dictionary...
            String definition = entry.getDefinition(); // finds the definition of each entry and
            for (String searchedWord : definitionSearch) { // for every word in the list of words that are being searched...
                if (definition.toLowerCase().contains(searchedWord)) { // if the full definition of an entry contains the word...
                    matchingEntries.add(entry); // add the entry to the matchingEntries array
                }
            }
        }

        // Print matching entries
        if (matchingEntries.isEmpty()) { // checks if matchingEntries is empty
            System.out.println(noMatches);
            logHistory.add(noMatches);
        } else {
            System.out.println(yesMatches);
            logHistory.add(yesMatches);
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
                logHistory.add(entry);
            }
        }
        System.out.println();

        // add to history list
        addToHistoryList(logHistory);
    }

    // ============ 3. FIND WORDS BEGINNING WITH... ===========
    public void findStartsWith() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find words that start with (separate multiple by commas): ");
        String prefixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
        String[] prefixSearch = prefixInput.split(",");
        logHistory.add("Find word(s) starting with: " + Arrays.toString(prefixSearch));

        // Load dictionary entries
        List<Word> dictionaryEntries = loadDictionary();

        // Search every dictionary entry definition for the words in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries
        for (Word entry : dictionaryEntries) { // for every line in my dictionary...
            String word = entry.getWord(); // find the word of each entry and
            for (String searchedWord : prefixSearch) { // for every word in the list of words that are being searched...
                if (word.toLowerCase().startsWith(searchedWord.toLowerCase().trim())) { // if the word starts with the substring...
                    matchingEntries.add(entry); // add the entry to the matchingEntries array
                }
            }
        }

        // Print matching entries
        if (matchingEntries.isEmpty()) { // checks if matchingEntries is empty
            System.out.println(noMatches);
            logHistory.add(noMatches);
        } else {
            System.out.println(yesMatches);
            logHistory.add(yesMatches);
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
                logHistory.add(entry);
            }
        }

        System.out.println();

        // add to history list
        addToHistoryList(logHistory);
    }

    // ============ 4. FIND WORDS ENDING WITH... ============
    public void findEndsWith() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find words that end with (separate multiple by commas): ");
        String suffixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
        String[] suffixSearch = suffixInput.split(",");
        logHistory.add("Find word(s) ending with: " + Arrays.toString(suffixSearch));

        // Load dictionary entries
        List<Word> dictionaryEntries = loadDictionary();

        // Search every dictionary entry definition for the words in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries
        for (Word entry : dictionaryEntries) { // for every line in my dictionary...
            String word = entry.getWord(); // find the word of each entry and
            for (String searchedWord : suffixSearch) { // for every word in the list of words that are being searched...
                if (word.toLowerCase().endsWith(searchedWord.toLowerCase().trim())) { // if the word ends with the substring...
                    matchingEntries.add(entry); // add the entry to the matchingEntries array
                }
            }
        }

        // Print matching entries
        if (matchingEntries.isEmpty()) { // checks if matchingEntries is empty
            System.out.println(noMatches);
            logHistory.add(noMatches);
        } else {
            System.out.println(yesMatches);
            logHistory.add(yesMatches);
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
                logHistory.add(entry);
            }
        }

        System.out.println();

        // add to history list
        addToHistoryList(logHistory);
    }


    // ============ 5. FIND ALL WORDS CONTAINING... ===========
    public void findWordsContaining() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the substring(s) you would like to find in a word. If more than one, please separate them by commas.");
        String substringInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] substringSearch = substringInput.split(",");; // creating an array to hold the words in the search
        logHistory.add("Find word(s) containing: " + Arrays.toString(substringSearch));

        // Load dictionary entries
        List<Word> dictionaryEntries = loadDictionary();

        // Search every dictionary entry definition for the words in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries
        for (Word entry : dictionaryEntries) { // for every line in my dictionary...
            String word = entry.getWord(); // find the word of each entry and
            for (String searchedWord : substringSearch) { // for every word in the list of words that are being searched...
                if (word.toLowerCase().contains(searchedWord.toLowerCase().trim())) { // if the full word of an entry contains the substring...
                    matchingEntries.add(entry); // add the entry to the matchingEntries array
                }
            }
        }

        // Print matching entries
        if (matchingEntries.isEmpty()) { // checks if matchingEntries is empty
            System.out.println(noMatches);
            logHistory.add(noMatches);
        } else {
            System.out.println(yesMatches);
            logHistory.add(yesMatches);
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
            }
        }
        System.out.println();

        addToHistoryList(logHistory);
    }

    // ============ 6. ADD A WORD =============
    public void addWord() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();
        String userChoice = "Word added to dictionary:";
        logHistory.add(userChoice);

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);

        // ask for new word
        System.out.println("Please enter a word to add to the dictionary: ");
        String wordInput = scanner.nextLine();

        // ask for the word definition
        System.out.println("Please enter the definition of the provided word: ");
        String definitionInput = scanner.nextLine();

        // ask for the part of speech
        System.out.println("Please enter the part of speech of the word: ");
        String partOfSpeechInput = scanner.nextLine();

        // ask for an example
        System.out.println("Please enter a sentence using the word: ");
        String exampleInput = scanner.nextLine();

        // Create a Word object with user inputs
        Word newWord = new Word(wordInput, definitionInput, partOfSpeechInput, exampleInput);

        // Load dictionary entries
        ArrayList<Word> dictionaryEntries = loadDictionary();

        // Add new word to array of dictionary entries
        dictionaryEntries.add(newWord);

        saveDictionary(dictionaryEntries);

        // add to history
        logHistory.add(newWord); // adding the word info to teh array
        addToHistoryList(logHistory);
    }

    // ============ 7. DELETE A WORD ==============
    public void deleteWord() throws IOException {
        // history section
        ArrayList<Object> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word(s) you would like to remove from the dictionary. If more than one, please separate them by commas.");
        String wordInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] wordsToDelete = wordInput.split(","); // turn search into a list of Strings
        logHistory.add("Word(s) deleted: " + Arrays.toString(wordsToDelete));


        // Load dictionary entries
        ArrayList<Word> dictionaryEntries = loadDictionary();

        // This will remove any entries that match any word that are in the array of wordsToDelete.
        dictionaryEntries.removeIf(entry -> { // remove an entry if...
            for (String word : wordsToDelete) { // for every word in the list of words to delete...
                if (entry.getWord().equalsIgnoreCase(word.trim())) { // if the word in the dictionary is the same as the word to delete.
                    System.out.println("Word '" + entry.getWord() + "' deleted.");
                    logHistory.add("Word '" + entry.getWord() + "' deleted.");
                    return true;
                }
            }
            return false;
        });
        System.out.println();
        saveDictionary(dictionaryEntries);

        // add to history
        addToHistoryList(logHistory);
    }

    // ============ 8. FIND HISTORY ==============
    private void addToHistoryList(ArrayList<Object> history) {
        historyList.add(history);
    }

    public void printHistory() {
        System.out.println("============= HISTORY =============");
        for (ArrayList<Object> historyObject: historyList) {
            for (Object element : historyObject) {
                System.out.println(element);
            }
            System.out.println();
        }
    }

    // ============ 9. CREATOR ==============
    public void printCreator() {

        System.out.println("\n" + "This dictionary app was created by Daniel Moreno." + "\n");
    }
}
