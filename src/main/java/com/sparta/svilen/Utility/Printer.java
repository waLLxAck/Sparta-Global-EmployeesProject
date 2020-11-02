package com.sparta.svilen.Utility;

public class Printer {
    public static void print(Object object) {
        System.out.println(object);
    }

    public static void printTimeTaken(long startTime) {
        System.out.println("Total time taken to add records: " + (System.nanoTime() - startTime)/1000000 + "ms.");
    }
}
