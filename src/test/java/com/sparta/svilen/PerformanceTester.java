package com.sparta.svilen;

import com.sparta.svilen.database.DatabaseDAO;
import org.junit.jupiter.api.Test;

public class PerformanceTester {

    @Test
    //65,000 records
    public void insertLargeFile() {
        DatabaseDAO db = new DatabaseDAO();
        db.start("EmployeeRecordsLarge");
    }

    @Test
    //10,000 records
    public void insertSmallFile() {
        DatabaseDAO db = new DatabaseDAO();
        db.start("EmployeeRecords");
    }
}
