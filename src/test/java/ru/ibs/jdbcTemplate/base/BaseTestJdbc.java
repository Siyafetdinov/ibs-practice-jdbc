package ru.ibs.jdbcTemplate.base;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.ibs.utils.UtilsDB;

public class BaseTestJdbc  {
    protected static DataBaseControlJdbc dataBaseControl =
            new DataBaseControlJdbc(new JdbcTemplate(UtilsDB.getDataSourceHikari()));
}
