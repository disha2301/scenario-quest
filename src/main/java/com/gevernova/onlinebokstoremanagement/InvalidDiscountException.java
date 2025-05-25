package com.gevernova.onlinebokstoremanagement;

// making use of this exception in cart class for calculation purpose
public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}