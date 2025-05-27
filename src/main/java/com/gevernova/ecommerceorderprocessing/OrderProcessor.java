package com.gevernova.ecommerceorderprocessing;

// Applies SRP and OCP: Handles order processing logic only
public class OrderProcessor {
    private final Inventory inventory;
    private final NotificationService notifier;

    public OrderProcessor(Inventory inventory, NotificationService notifier) {
        this.inventory = inventory;
        this.notifier = notifier;
    }

    // Validates and processes an order
    public void processOrder(Order order) {
        try {
            // First, validate stock for all items
            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();

                if (!inventory.isAvailable(product.getId(), quantity)) {
                    throw new IllegalArgumentException("Product " + product.getName() + " is out of stock.");
                }
            }

            // Deduct inventory
            for (OrderItem item : order.getItems()) {
                inventory.reduceStock(item.getProduct().getId(), item.getQuantity());
            }

            // Notify on success
            notifier.sendNotification("Order " + order.getOrderId() + " processed successfully.");
        } catch (Exception e) {
            // Notify on failure
            notifier.sendNotification("Order " + order.getOrderId() + " failed: " + e.getMessage());
        }
    }
}
