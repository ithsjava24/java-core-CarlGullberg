package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord)) return false;
        ProductRecord that = (ProductRecord) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price);
    }

    // Implementera hashCode() för att stödja användning i hash-baserade samlingar
    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, category, price);
    }

//     Implementera toString() för att underlätta felsökning
    @Override
    public String toString() {
        return "ProductRecord{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
