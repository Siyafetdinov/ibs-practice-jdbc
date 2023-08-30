package ru.ibs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import ru.ibs.dataProvider.AddProductDataProvider;
import ru.ibs.jdbc.base.BaseTest;
import ru.ibs.jdbcTemplate.base.BaseTestJdbc;
import ru.ibs.product.Food;

// Для проверки работы jdbcTemplate, изменить extends BaseTest -> extends BaseTestJdbc
public class AddAndDeleteProduct extends BaseTest {
    private final String NAME_TABLE = "Food";

    @DisplayName("Добавление товаров")
    @ParameterizedTest
    @ArgumentsSource(AddProductDataProvider.class)
    void addAndDelSQL(Food newFood) {

        // Проверяем доступ к таблице
        dataBaseControl.selectAllFrom(NAME_TABLE);

        // Добавляем новый товар
        dataBaseControl.newFood(newFood);

        // Проверяем добавленный товар в БД
        Assertions.assertEquals(
                newFood, dataBaseControl.getFood(newFood),
                "Товары не совпадают");

        // Удаляем товар
        dataBaseControl.delFood(newFood);

        // Проверяем удаленный товар в БД
        Assertions.assertNull(dataBaseControl.getFood(newFood),
                "Товар не был удален");
    }
}
