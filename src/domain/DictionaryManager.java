package domain;

import interfaces.WordReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManager {

    public DictionaryManager() throws IOException {
    }

    // Checks if the dictionary exists
    // If not, it will create one
    public void dictionaryExists() {
        File dictionaryFile = new File("./src/lib/dictionary.txt");

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
    }

    // ======== METHOD TO STORE CONTENT OF DICTIONARY IN AN ARRAY =============
    public List<String[]> loadDictionary() throws IOException {
        // Load dictionary from the file
        WordReader wordReader = new domain.WordReader();
        String fullDictionaryString = wordReader.readEntries("./lib/dictionary.txt"); // took what the method returned and assigned to a String variable

        // splitting the string and turning it into an array
        // each line is an element in the array
        String[] dictionaryEntries = fullDictionaryString.split("\\n");

        // Create an ArrayList to hold the arrays
        List<String[]> arrayList = new ArrayList<>();

        // Turn each line into a list that is split at the '|' symbol
        for (String entry : dictionaryEntries) {
            // split at the ' | '
            String[] wordInfo = entry.split(" \\| ");

            // add every line into an arrayList
            arrayList.add(wordInfo);
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public void findWord() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word(s) you would like to find. If more than one, please separate them by commas.");
        String wordInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] wordSearch; // creating an array to hold the words in the search

        if (wordInput.contains(",")) {
            // multiple words searched separated by commas
            wordSearch = wordInput.split(","); // turn search into a list of Strings
            for (String word : wordSearch) {
                if (word.trim().contains(" ")) { // trimming words then checking if it contains spaces within it
                    System.out.println("Error: please ensure there are no spaces in your words");
                    return;
                }
            }
        } else if (wordInput.contains(" ")) {
            // multiple words searched but they have spaces in them
            System.out.println("Error: please separate the words by commas or ensure there are no accidental spaces.");
            return;
        } else {
            // only one word searched
            wordSearch = new String[1]; // creates a string with one element
            wordSearch[0] = wordInput.trim(); // fills element with the one word that was searched
        }

        // Load dictionary entries
        List<String[]> dictionaryEntries = loadDictionary();

        // Search every dictionary entry for the words  in the wordSearch
        for (String[] entry : dictionaryEntries) {
            String dictionaryWord = entry[0]; // finds the first element of the entry, which is the word
            for (String word : wordSearch) {
                if (dictionaryWord.equalsIgnoreCase(word)) {
                    System.out.println(String.join(" | ", entry));
                }
            }
        }
    }
}
