package com.sparta.svilen.starter;

import com.sparta.svilen.database.DatabaseDAO;

public class Starter {
    public static void start() {
        DatabaseDAO db = new DatabaseDAO();

        db.start("EmployeeRecords");
//        db.start("EmployeeRecordsLarge");
    }
}
