package com.gevernova.onlinebokstoremanagement;

public class Customer {
    private String name;
    private Cart cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new Cart();
    }

    public String getName() { return name; }
    public Cart getCart() { return cart; }
}
