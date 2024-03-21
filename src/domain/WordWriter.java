package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordWriter implements interfaces.WordWriter {

    @Override
    public List<Word> writeEntries(Word word) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter("./lib/dictionary.txt", true);
            fileWriter.write(word.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
