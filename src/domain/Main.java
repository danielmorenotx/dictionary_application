package domain;

import interfaces.WordReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Display the menu
        Menu menu = new Menu();
        menu.menuHandler();
//
//        // ========= WORD READER ===========
//        WordReader wordReader = new domain.WordReader();
//        wordReader.readEntries("./lib/dictionary.txt");

//        DictionaryManager manager = new DictionaryManager();
//
//        try {
////            manager.dictionaryExists();
//            manager.loadDictionary();
//        } catch (IOException e) {
//            System.err.println("An error occurred while managing the dictionary: " + e.getMessage());
//        }

    }
}
