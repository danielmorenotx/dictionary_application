package domain;

public class Word {

    public String word;
    public String definition;
    public String partOfSpeech;
    public String exampleUsage;

    // Object constructor
    public Word(String word, String definition, String partOfSpeech, String exampleUsage) {
        this.word = word;
        this.definition = definition;
        this.partOfSpeech = partOfSpeech;
        this.exampleUsage = exampleUsage;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getExampleUsage() {
        return exampleUsage;
    }

    public void setExampleUsage(String exampleUsage) {
        this.exampleUsage = exampleUsage;
    }

    public static void main(String[] args) {
        Word example = new Word("example", "one of a number of things, or a part of something, taken to show the character of the whole", "noun", "This painting is an example of his early work.");

        System.out.println("The definition of " + example.getWord() + " is '" + example.getDefinition() + "'.");
        System.out.println("'Example' is a " + example.getPartOfSpeech() + ".");
        System.out.println("Used in a sentence: '" + example.getExampleUsage());

    }

}
