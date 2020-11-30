package com.sparta.svilen;

import java.util.ArrayList;

public class Starter {
    public static void start() {
        Person person = new Person();
        ArrayList<Thread> persons = createThreads(person);
        startThreads(persons);
    }

    private static ArrayList<Thread> createThreads (Person person) {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(person));
            threads.get(i).setName("Bob " + i);
        }
        return threads;
    }

    private static void startThreads(ArrayList<Thread> persons) {
        for (Thread person : persons) {
            person.start();
        }
    }
}
