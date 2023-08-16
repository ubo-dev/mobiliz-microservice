package com.ubo.groupingservice.exception;

public class CourierNotFoundException extends RuntimeException{
    public CourierNotFoundException(String message) {
        super(message);
    }
}
