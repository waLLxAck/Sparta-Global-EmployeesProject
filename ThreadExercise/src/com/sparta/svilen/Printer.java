package com.sparta.svilen;

import java.lang.Thread;

public class Printer {
    public static void printIds(int nhsId, int niId, int otherId) {
        System.out.println("I am " + Thread.currentThread().getName() + ". My NHS ID is: " +
                nhsId + ", my NI ID is: " + niId + " and my other ID is: " + otherId);
    }
}
