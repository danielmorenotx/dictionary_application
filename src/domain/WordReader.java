package domain;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordReader implements interfaces.WordReader {

    public String readEntries(String fileName) {

        try {
            String dictionaryFile = fileName;
            FileReader fileReader = new FileReader(dictionaryFile);
            int character = fileReader.read();
            String wholeDictionary = "";

            while (character != -1) {
                char currentCharacter = (char) character;
                wholeDictionary += currentCharacter;
                character = fileReader.read();
            }
//            System.out.println("Complete Dictionary:");
//            System.out.println(wholeDictionary);
            return wholeDictionary;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
