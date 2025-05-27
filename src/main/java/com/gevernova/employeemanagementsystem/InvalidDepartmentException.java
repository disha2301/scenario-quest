package com.gevernova.employeemanagementsystem;
// Custom exception for invalid department input
public class InvalidDepartmentException extends Exception {
    public InvalidDepartmentException(String message){
        super(message);
    }
}
