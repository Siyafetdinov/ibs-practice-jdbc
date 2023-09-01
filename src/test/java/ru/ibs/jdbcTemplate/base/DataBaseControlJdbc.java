package ru.ibs.jdbcTemplate.base;
import ru.ibs.product.Food;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DataBaseControlJdbc {
    private JdbcTemplate jdbcTemplate;

    public DataBaseControlJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void selectAllFrom(String nameTable) {
        jdbcTemplate.execute("SELECT * FROM " + nameTable);
    }

    public Food getFood(Food food) {
        String sql = "SELECT * FROM FOOD WHERE FOOD_NAME = ? AND FOOD_TYPE = ? AND FOOD_EXOTIC = ?";
        Object[] params = {food.getName(), food.getType(), food.isExotic()};
        RowMapper<Food> rowMapper = (resultSet, rowNum) -> new Food(
                resultSet.getString("FOOD_NAME"),
                resultSet.getString("FOOD_TYPE"),
                resultSet.getBoolean("FOOD_EXOTIC")
        );
        return jdbcTemplate.query(sql, params, rowMapper).stream().findFirst().orElse(null);
    }

    public void newFood(Food food) {
        String sql = "INSERT INTO FOOD VALUES ((SELECT MAX(FOOD_ID) + 1 FROM FOOD), ?, ?, ?)";
        Object[] params = {food.getName(), food.getType(), food.isExotic()};
        jdbcTemplate.update(sql, params);
    }

    public void delFood(Food food) {
        String sql = "DELETE FROM Food WHERE FOOD_NAME = ? AND FOOD_TYPE = ? AND FOOD_EXOTIC = ?";
        Object[] params = {food.getName(), food.getType(), food.isExotic()};
        jdbcTemplate.update(sql, params);
    }
}