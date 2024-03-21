package domain;

import interfaces.WordReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        // ========= TESTING WORK WRITER ==========
        WordWriter wordWriter = new WordWriter();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        Word newWord = new Word(userInput,"unknown","unknown","unknown");

        wordWriter.writeEntries(newWord);

        // ========= TESTING WORD READER ===========
        WordReader wordReader = new domain.WordReader();
        wordReader.readEntries("./lib/dictionary.txt");



    }
}
