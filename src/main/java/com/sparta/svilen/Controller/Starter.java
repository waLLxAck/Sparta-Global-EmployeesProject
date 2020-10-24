package com.sparta.svilen.Controller;

import com.sparta.svilen.DataAccessLayer.DatabaseDAO;

public class Starter {
    public static void start() {
        DatabaseDAO db = new DatabaseDAO();

        db.start("EmployeeRecordsLarge");

//        db.insertEmployee(new EmployeeDTO(10001, 5000, LocalDate.of(1995, 5, 3), LocalDate.now(), "Mr.", "John",
//        'X', "Bathe", "M", "john.bathe@gmail.com"));
    }
}
