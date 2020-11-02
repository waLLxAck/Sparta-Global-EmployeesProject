package com.sparta.svilen.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputCollector {
    public static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        Printer.displayReportChoice();
        Printer.displayChoicePrompt();
        do {
            try {
                input = scanner.nextInt();
                if (input < 1 || input > 3) {
                    throw new InputMismatchException("Invalid input");
                }
                return input;
            } catch (InputMismatchException ime) {
                Printer.displayError();
                Sleeper.sleep(100);
            }
        } while (true);
    }
}
