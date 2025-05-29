package com.gevernova.powerplantperformanceanalyzer;

// custom exception
public class DataCorruptionException extends Exception {
    public DataCorruptionException(String message){
        super(message);
    }
}
