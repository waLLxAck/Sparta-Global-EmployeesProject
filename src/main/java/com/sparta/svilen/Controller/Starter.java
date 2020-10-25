package com.sparta.svilen.Controller;

import com.sparta.svilen.DataAccessLayer.DatabaseDAO;

public class Starter {
    public static void start() {
        DatabaseDAO db = new DatabaseDAO();

        db.start("EmployeeRecords");
//        db.start("EmployeeRecordsLarge");
    }
}
