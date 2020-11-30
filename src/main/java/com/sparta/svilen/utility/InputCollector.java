package com.sparta.svilen.utility;

import java.util.Scanner;

public class InputCollector {
    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        Printer.displayReportChoice();
        Printer.displayChoicePrompt();
        do {
            try {
                input = scanner.nextLine();
                return input;
            } catch (Exception e) {
                displayErrorMessage();
            }
        } while (true);
    }

    private static void displayErrorMessage() {
        Printer.displayError();
        Sleeper.sleep(100);
    }
}
