package com.gevernova.onlinebokstoremanagement;

// making use of this exception in cart class for calculation purpose
public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}