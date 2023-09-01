package ru.ibs.dataProvider;
import ru.ibs.product.Food;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.util.stream.Stream;

public class AddProductDataProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(new Food("Манго", "FRUIT", true)),
                Arguments.of(new Food("Апельсин", "FRUIT", false)),
                Arguments.of(new Food("Картофель", "VEGETABLE", false)),
                Arguments.of(new Food("Огурец", "VEGETABLE", false)),
                Arguments.of(new Food("Помидор", "VEGETABLE", false)),
                Arguments.of(new Food("Арбуз", "FRUIT", false))
        );
    }
}
