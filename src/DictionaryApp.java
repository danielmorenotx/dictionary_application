import domain.DictionaryManager;
import domain.Menu;

import java.io.IOException;

public class DictionaryApp {
    public static void main(String[] args) throws IOException {

        try {
            DictionaryManager.loadDictionary(); // Load the dictionary
            Menu menu = new Menu();
            menu.displayMenu();
        } catch (IOException e) {
            throw new IOException("Error loading dictionary: " + e.getMessage()); // if the dictionary gets an IO exception, program dies
        }
    }
}
