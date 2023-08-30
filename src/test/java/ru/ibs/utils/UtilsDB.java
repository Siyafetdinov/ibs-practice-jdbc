package ru.ibs.utils;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilsDB {
    private static final String PATH_TO_PROPERTIES_BD = "src/test/resources/db.properties";
    public static DataSource getDataSource() {
        Properties properties = new Properties();
        JdbcDataSource dataSource = new JdbcDataSource();

        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES_BD));
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES_BD + " не обнаружен");
            e.printStackTrace();
        }
        return dataSource;
    }
}

