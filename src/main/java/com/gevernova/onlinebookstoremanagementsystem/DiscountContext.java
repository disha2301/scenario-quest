package com.gevernova.onlinebookstoremanagementsystem;

public class DiscountContext {
    private DiscountCalculator calculator;

    public DiscountContext(DiscountCalculator calculator){
        this.calculator = calculator;
    }
    public double applyDiscount(double total) throws InvalidDiscountException{
        return calculator.calculate(total);
    }
}
