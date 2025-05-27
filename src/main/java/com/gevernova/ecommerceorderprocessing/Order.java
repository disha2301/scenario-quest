package com.gevernova.ecommerceorderprocessing;

import java.util.List;

// Represents a customer's order consisting of multiple items
public class Order {
    private final String orderId;
    private final List<OrderItem> items;

    public Order(String orderId, List<OrderItem> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public String getOrderId() { return orderId; }
    public List<OrderItem> getItems() { return items; }
}
