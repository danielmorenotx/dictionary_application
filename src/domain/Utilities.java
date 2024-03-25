package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utilities {

    public static String partOfSpeechValidator() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. noun - the name of a person, place, thing, or idea.\n" +
                    "2. verb - expresses action or being.\n" +
                    "3. adjective - modifies or describes a noun or pronoun.\n" +
                    "4. adverb - modifies or describes a verb, an adjective, or another adverb.\n" +
                    "5. pronoun - a word used in place of a noun.\n" +
                    "6. preposition - a word placed before a noun or pronoun to form a phrase modifying another word in the sentence.\n" +
                    "7. conjunction - joins words, phrases, or clauses.\n" +
                    "8. interjection - a word used to express emotion.\n");

            // Read user input
            int partOfSpeechInput = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (partOfSpeechInput) {
                case 1:
                    return "noun";
                case 2:
                    return "verb";
                case 3:
                    return "adjective";
                case 4:
                    return "adverb";
                case 5:
                    return "pronoun";
                case 6:
                    return "preposition";
                case 7:
                    return "conjunction";
                case 8:
                    return "interjection";
                default:
                    throw new IllegalArgumentException("Invalid Input. Please enter the correct number corresponding to the word's part of speech:\n");

            }
        }
    }

}
