package com.gevernova.onlinebookstoremanagementsystem;

// CartPriceCalculator.java - SRP: Calculates cart total price
public class CartPriceCalculator {
    public double calculateTotal(Cart cart){
        return cart.getItems().stream().mapToDouble(Book::getPrice).sum();
    }
}
