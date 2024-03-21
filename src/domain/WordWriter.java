package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class WordWriter implements interfaces.WordWriter {

    @Override
    public List<Word> writeEntries() throws IOException {
        Scanner scanner = new Scanner(System.in);

        // ask for new word
        System.out.println("Please enter a word to add to the dictionary: ");
        String wordInput = scanner.nextLine();

        // ask for the word definition
        System.out.println("Please enter the definition of the provided word: ");
        String definitionInput = scanner.nextLine();

        // ask for the part of speech
        System.out.println("Please enter the part of speech of the word: ");
        String partOfSpeechInput = scanner.nextLine();

        // ask for an example
        System.out.println("Please enter a sentence using the word: ");
        String exampleInput = scanner.nextLine();

        // Create a Word object with user inputs
        Word newWord = new Word(wordInput, definitionInput, partOfSpeechInput, exampleInput);

        try {
            FileWriter fileWriter = new FileWriter("./lib/dictionary.txt", true);
            fileWriter.write(newWord.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
