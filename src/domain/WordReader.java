package domain;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordReader implements interfaces.WordReader {

    public String readEntries(String fileName) throws IOException {

        try {
            String exampleFile = fileName;
            FileReader fileReader = new FileReader(exampleFile);
            int character = fileReader.read();
            String wholeText = "";

            while (character != -1) {
                char currentCharacter = (char) character;
                wholeText += currentCharacter;
                character = fileReader.read();
            }
            System.out.println(wholeText);
            return wholeText;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    };

}
