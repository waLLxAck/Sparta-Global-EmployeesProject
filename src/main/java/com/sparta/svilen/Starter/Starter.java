package com.sparta.svilen.Starter;

import com.sparta.svilen.DataAccess.DatabaseDAO;

public class Starter {
    public static void start() {
        DatabaseDAO db = new DatabaseDAO();

        db.start("EmployeeRecords");
//        db.start("EmployeeRecordsLarge");
    }
}
