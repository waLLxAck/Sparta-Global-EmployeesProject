package com.sparta.svilen.starter;

import com.sparta.svilen.database.DatabaseDAO;
import com.sparta.svilen.utility.InputCollector;

public class Starter {
    public static void start() {
        chooseFile(InputCollector.getUserInput());
    }

    private static void chooseFile(String input) {
        DatabaseDAO db = new DatabaseDAO();
        db.start(input);
    }
}
