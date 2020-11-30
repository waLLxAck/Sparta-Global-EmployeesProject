package com.sparta.svilen.utility;

public class Printer {
    public static void print(Object object) {
        System.out.println(object);
    }

    public static void printTimeTaken(long startTime) {
        System.out.println("Total time taken to add records: " + (System.nanoTime() - startTime)/1000000 + "ms.");
    }

    public static void displayReportChoice(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("Please choose a file to be inserted! (Options listed below)");
        System.out.print("--------------------------------------------------------------");
        System.out.println(FileRetriever.getFileString());
        System.out.println("--------------------------------------------------------------");
    }

    public static void displayChoicePrompt() {
        System.out.print("File name: ");
    }

    public static void displayError() {
        System.err.println("Invalid input. Please enter a number with characters ranging from [0-9].");
    }
}
