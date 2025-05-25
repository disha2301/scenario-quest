package com.gevernova.onlinebokstoremanagement;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double total) {
        return total;
    }
}
