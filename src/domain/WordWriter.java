package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordWriter implements interfaces.WordWriter {

    @Override
    public void writeEntries(List<Word> dictionaryEntries) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("./lib/dictionary.txt", true);

            for (Word entry : dictionaryEntries) {
                fileWriter.write(entry.getWord() + " | " +
                        entry.getDefinition() + " | " +
                        entry.getPartOfSpeech() + " | " +
                        entry.getExampleUsage() + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
