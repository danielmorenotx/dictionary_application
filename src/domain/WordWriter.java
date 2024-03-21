package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordWriter implements interfaces.WordWriter {

    @Override
    public List<Word> writeEntries(String word) throws IOException {

        try {
            Word newWord = new Word(word, "","","");
            FileWriter fileWriter = new FileWriter("./lib/example.txt", true);
            fileWriter.write(word);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
