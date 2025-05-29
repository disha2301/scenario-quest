package com.gevernova.smartgridloadbalancer;

// Custom exception for grid overload situations
public class GridOverloadException extends Exception {
    public GridOverloadException(String message){
        super(message);
    }
}
