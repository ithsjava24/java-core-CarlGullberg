
package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {
    private static final Map<String, Category> instances = new HashMap<>();
    private final String name;

    // Privat konstruktor för att förhindra extern instansiering
    private Category(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    // Fabriksmetod för att returnera samma instans för samma namn
    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        // Returnerar existerande instans eller skapar en ny om den inte finns
        return instances.computeIfAbsent(name.toLowerCase(), key -> new Category(name));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    // Hämtar namn med versal första bokstav som redan hanteras i konstruktor
    public String getName() {
        return name;
    }
}