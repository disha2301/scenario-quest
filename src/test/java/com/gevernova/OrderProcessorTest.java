package com.gevernova;

import com.gevernova.ecommerceorderprocessing.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class OrderProcessorTest {

    @Test
    public void testOrderProcessedSuccessfully() {
        Inventory inventory = new Inventory();
        Product p = new Product("201", "Keyboard");
        inventory.addProduct(p, 5);

        OrderItem item = new OrderItem(p, 2);
        Order order = new Order("ORD002", Arrays.asList(item));

        NotificationService dummyNotifier = msg -> {}; // No-op for test
        OrderProcessor processor = new OrderProcessor(inventory, dummyNotifier);

        processor.processOrder(order);
        assertTrue(inventory.isAvailable(p.getId(), 3)); // Should have 3 left
    }

    @Test
    public void testOrderFailsWhenOutOfStock() {
        Inventory inventory = new Inventory();
        Product p = new Product("202", "Monitor");
        inventory.addProduct(p, 1);

        OrderItem item = new OrderItem(p, 3);
        Order order = new Order("ORD003", Arrays.asList(item));

        NotificationService dummyNotifier = msg -> {};
        OrderProcessor processor = new OrderProcessor(inventory, dummyNotifier);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            // Simulate inner call
            for (OrderItem i : order.getItems()) {
                inventory.reduceStock(i.getProduct().getId(), i.getQuantity());
            }
        });

        assertTrue(e.getMessage().contains("Insufficient stock"));
    }
}
