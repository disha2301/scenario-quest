package com.gevernova.onlinebokstoremanagement;

public class PercentageDiscount implements DiscountStrategy {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double applyDiscount(double total) {
        return total - (total * percent / 100);
    }
}
