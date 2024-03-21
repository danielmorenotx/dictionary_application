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



}
