package com.gevernova.powerplantperformanceanalyzer;

// custom exception
public class MissingReadingException extends Exception {
    public MissingReadingException(String message){
        super(message);
    }
}
