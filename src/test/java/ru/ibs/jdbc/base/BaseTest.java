package ru.ibs.jdbc.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static DataBaseControl dataBaseControl = new DataBaseControl();

    @BeforeAll
    static void beforeAll() {
        dataBaseControl.openConnection();
    }

    @AfterAll
    static void afterAll() {
        dataBaseControl.closeConnection();
    }
}