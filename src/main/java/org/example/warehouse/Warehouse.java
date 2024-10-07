package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class Warehouse {
    private static Warehouse instance;
    private final String name;
    private List<ProductRecord> products = new ArrayList<>();
    private final Set<UUID> changedProductIds = new HashSet<>();



    private Warehouse() {
        this.name = "MyStore";
    }

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }else {
            instance.clearProducts();
        }
        return instance;
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }


    public boolean isEmpty() {
        return products.isEmpty();

    }


    public void clearProducts() {
        products.clear();
        changedProductIds.clear();
    }





    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }
UUID finalId = id;
        if (products.stream().anyMatch(product -> product.uuid().equals(finalId))) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord product = new ProductRecord(id, name, category, price);
        products.add(product);
        return product;
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return products.stream().filter(product -> product.uuid().equals(id)).findFirst();

    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        ProductRecord product = getProductById(id).orElseThrow(() ->
                new IllegalArgumentException("Product with that id doesn't exist."));

        product.setPrice(newPrice);
        changedProductIds.add(id);
    }

    public List<ProductRecord> getChangedProducts() {
        return products.stream()
                .filter(product -> changedProductIds.contains(product.uuid()))
                .collect(Collectors.toList());
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }


}
