package ru.ibs.jdbcTemplate.base;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestJdbc {
    protected static DataBaseControlJdbc dataBaseControl = new DataBaseControlJdbc();

    @BeforeAll
    static void beforeAll() {
        dataBaseControl.openConnection();
    }
}

