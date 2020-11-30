package com.sparta.svilen;

public class Person implements Runnable {
    static int count = 0;

    @Override
    public synchronized void run() {
        int nhsId = count;
        int niId = count+1;
        int otherId = count+2;
        printIds(nhsId, niId, otherId);
        setCount(count+3);
    }

    public static void setCount(int count) {
        Person.count = count;
    }

    public static void printIds(int nhsId, int niId, int otherId) {
        Printer.printIds(nhsId, niId, otherId);
    }
}
