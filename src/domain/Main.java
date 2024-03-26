package domain;

import interfaces.WordReader;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Display the menu
        Menu menu = new Menu();
        menu.enterToContinue();
    }
}
