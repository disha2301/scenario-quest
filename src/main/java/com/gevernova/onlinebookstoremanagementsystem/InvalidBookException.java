package com.gevernova.onlinebookstoremanagementsystem;

// InvalidBookException.java - Custom exception for cart operations
public class InvalidBookException extends Exception {
    public InvalidBookException(String message){
        super(message);
    }
}
