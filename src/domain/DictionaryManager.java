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

//    public void findStartsWith() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Find words that start with: ");
//        String prefixSearch = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
//
//        // Load dictionary entries
//        List<String[]> dictionaryEntries = loadDictionary();
//
//        // Search for words starting with the prefix
//        List<String[]> entriesWithPrefix = new ArrayList<>(); // creating array list that has words starting with the prefix
//
//        for (String[] entry : dictionaryEntries) {
//            String word = entry[0]; // the word is at index 0 of every list
//            if (word.toLowerCase().startsWith(prefixSearch.toLowerCase())) {
//                entriesWithPrefix.add(entry); // add the matching entries to the wordsWithPrefix arrayList
//            }
//        }
//
//        // Printing matching entries
//        if (entriesWithPrefix.isEmpty()) { // checks if the arrayList is empty
//            System.out.println("There are no words starting with '" + prefixSearch + "'.");
//        } else {
//            for (String[] entry : entriesWithPrefix) {
//                System.out.println(String.join(" | ", entry));
//            }
//        }
//    }
//
//    public void findEndsWith() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Find words that end with: ");
//        String suffixSearch = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
//
//        // Load dictionary entries
//        List<String[]> dictionaryEntries = loadDictionary();
//
//        // Search for words starting with the prefix
//        List<String[]> entriesWithSuffix = new ArrayList<>(); // creating array list that has words starting with the prefix
//
//        for (String[] entry : dictionaryEntries) {
//            String word = entry[0]; // the word is at index 0 of every list
//            if (word.toLowerCase().endsWith(suffixSearch.toLowerCase())) {
//                entriesWithSuffix.add(entry); // add the matching entries to the wordsWithPrefix arrayList
//            }
//        }
//
//        // Printing matching entries
//        if (entriesWithSuffix.isEmpty()) { // checks if the arrayList is empty
//            System.out.println("There are no words ending with '" + suffixSearch + "'.");
//        } else {
//            for (String[] entry : entriesWithSuffix) {
//                System.out.println(String.join(" | ", entry));
//            }
//        }
//    }
//
//    public void findSubstring() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter the substring(s) you would like to find in a word. If more than one, please separate them by commas.");
//        String substringInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
//        String[] substringSearch; // creating an array to hold the words in the search
//
//        if (substringInput.contains(",")) {
//            // multiple words searched separated by commas
//            substringSearch = substringInput.split(","); // turn search into a list of Strings
//            for (String word : substringSearch) {
//                if (word.trim().contains(" ")) { // trimming words then checking if it contains spaces within it
//                    System.out.println("Error: please ensure there are no spaces in your substrings");
//                    return;
//                }
//            }
//        } else if (substringInput.contains(" ")) {
//            // multiple words searched but they have spaces in them
//            System.out.println("Error: please separate the substrings by commas or ensure there are no spaces in your substrings.");
//            return;
//        } else {
//            // only one word searched
//            substringSearch = new String[1]; // creates a string with one element
//            substringSearch[0] = substringInput.trim(); // fills element with the one word that was searched
//        }
//
//        // Load dictionary entries
//        List<String[]> dictionaryEntries = loadDictionary();
//
//        // Search every dictionary entry for the words  in the wordSearch
//        for (String[] entry : dictionaryEntries) {
//            String dictionaryWord = entry[0]; // finds the first element of the entry, which is the word
//            for (String substring : substringSearch) {
//                if (dictionaryWord.toLowerCase().contains(substring.toLowerCase())) {
//                    System.out.println(String.join(" | ", entry));
//                }
//            }
//        }
//    }

}
