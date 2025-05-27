package com.gevernova.ecommerceorderprocessing;

import java.util.HashMap;
import java.util.Map;

// Manages product stock using Map<ProductId, Quantity>
public class Inventory {
    private final Map<String, Integer> stock = new HashMap<>();

    // Add or restock product
    public void addProduct(Product product, int quantity) {
        stock.put(product.getId(), stock.getOrDefault(product.getId(), 0) + quantity);
    }

    // Check if product has sufficient stock
    public boolean isAvailable(String productId, int quantity) {
        return stock.getOrDefault(productId, 0) >= quantity;
    }

    // Reduce stock after successful order
    public void reduceStock(String productId, int quantity) {
        if (!isAvailable(productId, quantity)) {
            throw new IllegalArgumentException("Insufficient stock for product ID: " + productId);
        }
        stock.put(productId, stock.get(productId) - quantity);
    }

    // Summary report using Java 8 streams
    public void printInventorySummary() {
        stock.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .forEach(entry -> System.out.println("Product ID: " + entry.getKey() + ", Stock: " + entry.getValue()));
    }
}
