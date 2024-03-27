package domain;

public class Utilities {

    public static boolean isValidWord(String word) {
        return word.toLowerCase().matches("[a-z]+"); // checks if word is only letters
    }

    public static boolean isValidDefinitionOrExample(String definitionOrExample) {
        return definitionOrExample.split(" ").length > 2; // checks if the definition has more than two words
    }

    public static String convertPartOfSpeechMenuChoice(String menuChoice) {

        switch (menuChoice) {
            case "1":
                return "noun";
            case "2":
                return "verb";
            case "3":
                return "adjective";
            case "4":
                return "adverb";
            case "5":
                return "pronoun";
            case "6":
                return "preposition";
            case "7":
                return "conjunction";
            case "8":
                return "interjection";
            default:
                System.out.println("Invalid Input. Please enter the correct number corresponding to the word's part of speech:\n");
                return "invalid";

        }
    }

    public static boolean newEntryConfirmation(String confirmation) {
        return confirmation.isEmpty();
    }
}

