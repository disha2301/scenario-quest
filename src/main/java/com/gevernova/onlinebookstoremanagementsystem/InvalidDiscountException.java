package com.gevernova.onlinebookstoremanagementsystem;

// InvalidDiscountException.java - Custom exception for discount logic

public class InvalidDiscountException extends Exception {
    public InvalidDiscountException(String message){
        super(message);
    }
}
