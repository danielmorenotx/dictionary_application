package domain;

import interfaces.WordReader;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static domain.Menu.*;

public class DictionaryManager {
    private ArrayList<ArrayList<String>> historyList = new ArrayList<>();
    private static ArrayList<Word> dictionaryEntries; // ArrayList to store entries once loaded

    // ======== METHOD TO STORE CONTENT OF DICTIONARY IN AN ARRAY =============
    public static ArrayList<Word> loadDictionary() throws IOException { // returns list of word objects
//      First checks if the dictionary exists
        File dictionaryFile = new File("./lib/dictionary.txt");
        if (!dictionaryFile.exists()) { // checks if the file DOES NOT exist. If it does, this block is skipped.
            if (dictionaryFile.createNewFile()) { // will throw exception if file does not exist and cannot be created
                System.out.println("Successfully created a new dictionary!" + "\n");
            } else {
                System.out.println("Dictionary already exists!" + "\n"); // will return false if it exists already
            }
        }

        // Create string from contents of dictionary
        WordReader wordReader = new domain.WordReader();
        String fullDictionary = wordReader.readEntries("./lib/dictionary.txt"); // took what the method returned and assigned to a String variable

        // splitting the string and turning it into an array
        // each line is an element in the array
        String[] dictionaryEntries = fullDictionary.split("\\n");

        // turns to a stream, then splits every element into 4 parts at the | symbol, then creates a word object from those
        ArrayList<Word> words = Arrays.stream(dictionaryEntries)
                .map(entry -> entry.split(" \\| "))
                .map(splitEntry -> new Word(splitEntry[0], splitEntry[1], splitEntry[2], splitEntry[3]))
                .collect(Collectors.toCollection(ArrayList::new));

        return words; // ArrayList of Word objects
    }

    // get loaded dictionary
    public static ArrayList<Word> getDictionary() throws IOException {
        if (dictionaryEntries == null) { // if dictionary has not been given a value (has not been given the loadDictionary value)...
            dictionaryEntries = loadDictionary(); // give dictionaryEntries the value of the ArrayList of Word objects created in loadDictionary
        }
        return dictionaryEntries; // return the content of the dictionary as an ArrayList
    }


    // ============ 1. FIND WORDS ===========
    public void findWord(String[] wordSearch) throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        logHistory.add("Searched word(s): " + String.join(", ", wordSearch));

        // Load dictionary entries
        ArrayList<Word> dictionaryEntries = getDictionary();

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

        // Print matching entries, log results
        System.out.println(Menu.printMatchingEntries(matchingEntries) + "\n");

        // add to history list
        logHistory.add(Menu.printMatchingEntries(matchingEntries));
        addToHistoryList(logHistory);
    }

    // ============ 2. FIND DEFINITIONS ===========
    public void findDefinition(String[] wordOrPhrase) throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        logHistory.add("Searched definitions for: " + String.join(", ", wordOrPhrase));

        // Search every dictionary entry definition for the words in the wordSearch
        List<Word> matchingEntries = new ArrayList<>(); // empty list to hold matching entries

        for (Word entry : dictionaryEntries) { // for every line in my dictionary...
            String definition = entry.getDefinition(); // finds the definition of each entry and
            for (String searchedWord : wordOrPhrase) { // for every word in the list of words that are being searched...
                if (definition.toLowerCase().contains(searchedWord)) { // if the full definition of an entry contains the word...
                    matchingEntries.add(entry); // add the entry to the matchingEntries array
                }
            }
        }

        // Print matching entries, log results
        System.out.println(Menu.printMatchingEntries(matchingEntries) + "\n");

        // add to history list
        logHistory.add(Menu.printMatchingEntries(matchingEntries));
        addToHistoryList(logHistory);
    }

    // ============ 3. FIND WORDS BEGINNING WITH... ===========
    public void findStartsWith(String[] prefixSearch) throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        logHistory.add("Find word(s) starting with: " + String.join(", ", prefixSearch));

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

        // Print matching entries, log results
        System.out.println(Menu.printMatchingEntries(matchingEntries) + "\n");

        // add to history list
        logHistory.add(Menu.printMatchingEntries(matchingEntries));
        addToHistoryList(logHistory);
    }

    // ============ 4. FIND WORDS ENDING WITH... ============
    public void findEndsWith(String[] suffixSearch) throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        logHistory.add("Find word(s) ending with: " + String.join(", ", suffixSearch));

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

        // Print matching entries, log results
        System.out.println(Menu.printMatchingEntries(matchingEntries) + "\n");

        // add to history list
        logHistory.add(Menu.printMatchingEntries(matchingEntries));
        addToHistoryList(logHistory);
    }


    // ============ 5. FIND ALL WORDS CONTAINING... ===========
    public void findWordsContaining(String[] substringSearch) throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        logHistory.add("Find word(s) containing: " + String.join(", ", substringSearch));

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

        // Print matching entries, log results
        System.out.println(Menu.printMatchingEntries(matchingEntries) + "\n");

        // add to history list
        logHistory.add(Menu.printMatchingEntries(matchingEntries));
        addToHistoryList(logHistory);
    }

    // ============ 6. ADD A WORD =============
    public void addWord() throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();
        String userChoice = "Word added to dictionary:";
        logHistory.add(userChoice);

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        Word newWord = null;

        // Create a Word object with user inputs
        while (continueLoop) {
            String wordInput = promptNewWord();
            String definitionInput = promptNewDefinition();
            String partOfSpeechInput = promptNewPartOfSpeech();
            String exampleInput = promptNewExample();

            continueLoop = Menu.confirmNewWord(wordInput, definitionInput, partOfSpeechInput, exampleInput);

            newWord = new Word(wordInput, definitionInput, partOfSpeechInput, exampleInput);
        }


        // ==== UPDATE DICTIONARY ====
        ArrayList<Word> dictionaryEntries = getDictionary(); // loads dictionary as an ArrayList
        dictionaryEntries.add(newWord); // adds the newWord Word object to the ArrayList of loaded entries
        WordWriter wordWriter = new WordWriter();
        wordWriter.writeEntries(dictionaryEntries); // runs the saveDictionary method with the updated ArrayList of dictionaryEntries
        System.out.println("'" + newWord + "' successfully added to dictionary!\n");

        // ==== UPDATE HISTORY ====
        logHistory.add(newWord.toString()); // adding the word info to the history array
        addToHistoryList(logHistory); // adding the history array to the complete history list
    }

    // ============ 7. DELETE A WORD ==============
    public void deleteWord() throws IOException {
        // history section
        ArrayList<String> logHistory = new ArrayList<>();

        // ===== Main section =====
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word(s) you would like to remove from the dictionary. If more than one, please separate them by commas.");
        String wordInput = scanner.nextLine().trim(); // trim whatever input so it has no trailing spaces
        String[] wordsToDelete = wordInput.split(","); // turn search into a list of Strings
        logHistory.add("Word(s) deleted: " + String.join(", ", wordsToDelete));


        // Load dictionary entries
        ArrayList<Word> dictionaryEntries = getDictionary();

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

        // updating dictionary.txt file
        WordWriter wordWriter = new WordWriter();
        wordWriter.writeEntries(dictionaryEntries);

        // add to history
        addToHistoryList(logHistory);
    }

    // ============ 8. FIND HISTORY ==============
    private void addToHistoryList(ArrayList<String> history) {
        historyList.add(history);
    }

    public void printHistory() {
        System.out.println("============= HISTORY =============");
        for (ArrayList<String> historyLog: historyList) {
            for (String line : historyLog) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

}
