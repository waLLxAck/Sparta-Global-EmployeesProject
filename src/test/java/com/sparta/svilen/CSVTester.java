package com.sparta.svilen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CSVTester {
    @Test
    public void checkIfFilesExists() {
        check("EmployeeRecords");
        check("EmployeeRecordsLarge");
    }

    public void check(String fileName) {
        File file = new File("resources/" + fileName + ".csv");
        Assertions.assertNotNull(file);
    }
}
