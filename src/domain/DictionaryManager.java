package domain;

import interfaces.WordReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class DictionaryManager {


    // ======== METHOD TO STORE CONTENT OF DICTIONARY IN AN ARRAY =============
    public List<Word> loadDictionary() throws IOException { // returns list of word objects

        // First checks if the dictionary exists
        File dictionaryFile = new File("./lib/dictionary.txt");
        if (!dictionaryFile.exists()) {
            try {
                if (dictionaryFile.createNewFile()) {
                    System.out.println("Successfully created a new dictionary");
                } else {
                    System.out.println("Could not create a new dictionary :(");
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

        List<Word> words = Arrays.stream(dictionaryEntries)
                .map(entry -> entry.split(" \\| "))
                .map(splitEntry -> new Word(splitEntry[0], splitEntry[1], splitEntry[2], splitEntry[3]))
                .toList();

        return words;
    }

    public void findWord() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word(s) you would like to find. If more than one, please separate them by commas.");
        String wordInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] wordSearch = wordInput.split(","); // turn search into a list of Strings

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
            System.out.println("No matching words found.");
        } else {
            System.out.println("Matching entries:");
            for (Word entry : matchingEntries) {
                System.out.println(entry);
            }
        }
    }

    public void findDefinition() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter word(s) you would like to find in definitions. If more than one, please separate them by commas.");
        String definitionInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] definitionSearch = definitionInput.split(",");; // creating an array to hold the words in the search

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
            System.out.println("No matching definitions found.");
        } else {
            System.out.println("Matching entries:");
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
            }
        }
    }

    public void findStartsWith() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find words that start with (separate multiple by commas): ");
        String prefixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
        String[] prefixSearch = prefixInput.split(",");

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
            System.out.println("No matching words found.");
        } else {
            System.out.println("Matching entries:");
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
            }
        }
    }

    public void findEndsWith() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Find words that start with (separate multiple by commas): ");
        String suffixInput = scanner.nextLine(); // trim whatever input so it has no trailing spaces
        String[] suffixSearch = suffixInput.split(",");

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
            System.out.println("No matching words found.");
        } else {
            System.out.println("Matching entries:");
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
            }
        }
    }


    // ============ FIND ALL WORDS CONTAINING... ===========
    public void findWordsContaining() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the substring(s) you would like to find in a word. If more than one, please separate them by commas.");
        String substringInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] substringSearch = substringInput.split(",");; // creating an array to hold the words in the search

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
            System.out.println("No matching words found.");
        } else {
            System.out.println("Matching entries:");
            for (Word entry : matchingEntries) {
                System.out.println(entry); // prints every entry in the matchingEntries list
            }
        }
    }

}
