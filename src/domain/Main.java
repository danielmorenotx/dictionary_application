package domain;

import interfaces.WordReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // Display the menu
        Menu menu = new Menu();
        menu.menuHandler();

        // ========= WORD WRITER ==========
        WordWriter wordWriter = new WordWriter();
        wordWriter.writeEntries();

        // ========= WORD READER ===========
        WordReader wordReader = new domain.WordReader();
        wordReader.readEntries("./lib/dictionary.txt");

    }
}
