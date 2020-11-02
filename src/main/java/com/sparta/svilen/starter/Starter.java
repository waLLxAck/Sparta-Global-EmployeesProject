package com.sparta.svilen.starter;

import com.sparta.svilen.database.DatabaseDAO;
import com.sparta.svilen.utility.InputCollector;

public class Starter {
    public static void start() {
        chooseFile(InputCollector.getUserInput());
    }

    private static void chooseFile(int input) {
        DatabaseDAO db = new DatabaseDAO();

        switch (input) {
            case 1:
                db.start("EmployeeRecords");
                break;
            case 2:
                db.start("EmployeeRecordsLarge");
                break;
            default:
                break;
        }
    }
}
