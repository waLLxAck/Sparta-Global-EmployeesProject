package com.sparta.svilen;

import com.sparta.svilen.Model.DatabaseDAO;

public class Starter {
    public static void start() {
        DatabaseDAO db = new DatabaseDAO();
        db.insertEmployeesFromFile();
    }
}
