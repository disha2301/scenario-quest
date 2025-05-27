package com.gevernova.onlinebookstoremanagementsystem;

// DiscountCalculator.java - Open closed principle: Interface to allow extension

// custom exception used

public interface DiscountCalculator {
    double calculate(double total) throws InvalidDiscountException;
}
