package com.gevernova.onlinebookstoremanagementsystem;

// FlatDiscountCalculator.java - encapsulation : Applies flat discount
public class FlatDiscountCalculator implements DiscountCalculator {
    private double discount;

    // constructor
    public FlatDiscountCalculator(double discount) {
        this.discount = discount;
    }
    // interface implementation
    @Override
    public double calculate(double total) throws InvalidDiscountException{
        if(discount < 0) throw new InvalidDiscountException("Negative discount value entered");
        return Math.max(0, total - discount);
    }
}

