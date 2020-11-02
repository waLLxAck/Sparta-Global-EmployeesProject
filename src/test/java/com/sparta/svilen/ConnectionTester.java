package com.sparta.svilen;

import com.sparta.svilen.database.DatabaseDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionTester {
    @Test
    public void hasEstablishedDatabaseConnection() {
        DatabaseDAO db = new DatabaseDAO();
        db.getConnection();
        Assertions.assertNotNull(db.connection);
    }
}
