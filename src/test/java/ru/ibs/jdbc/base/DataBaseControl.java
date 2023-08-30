package ru.ibs.jdbc.base;

import ru.ibs.product.Food;
import ru.ibs.utils.UtilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseControl {
    private Connection connection = null;

    public void openConnection() {
        try {
            connection = UtilsDB.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectAllFrom(String nameTable) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + nameTable)) {
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Не получилось выгрузить базу данных");
        }
    }

    public Food getFood(Food food) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FOOD WHERE FOOD_NAME = ? AND FOOD_TYPE = ? AND FOOD_EXOTIC = ?")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getType());
            preparedStatement.setBoolean(3, food.isExotic());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Food(resultSet.getString("FOOD_NAME"), resultSet.getString("FOOD_TYPE"), resultSet.getBoolean("FOOD_EXOTIC"));
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Не получилось получить товар: Имя - " + food.getName() + " Тип - " + food.getType() + " Экзотический - " + food.isExotic());
        }
    }

    public void newFood(Food food) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FOOD VALUES ((SELECT MAX(FOOD_ID) FROM FOOD+1), ?, ?, ?)")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getType());
            preparedStatement.setBoolean(3, food.isExotic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не получилось загрузить в БД товар: " + food.toString());
        }
    }

    public void delFood(Food food) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Food WHERE FOOD_NAME = ? AND FOOD_TYPE = ? AND FOOD_EXOTIC = ?")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getType());
            preparedStatement.setBoolean(3, food.isExotic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Не получилось удалить из БД товар: " + food.toString());
        }
    }
}

