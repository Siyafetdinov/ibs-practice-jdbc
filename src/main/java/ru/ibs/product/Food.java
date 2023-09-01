package ru.ibs.product;

import java.util.Objects;

public class Food {
    private String name;
    private String type;
    private boolean isExotic;

    public Food(String name, String type, boolean isExotic) {
        this.name = name;
        this.type = type;
        this.isExotic = isExotic;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isExotic() {
        return isExotic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return isExotic == food.isExotic && Objects.equals(name, food.name) && Objects.equals(type, food.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, isExotic);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isExotic=" + isExotic;
    }
}
