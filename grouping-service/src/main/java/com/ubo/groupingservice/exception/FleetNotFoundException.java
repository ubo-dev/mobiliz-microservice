package com.ubo.groupingservice.exception;

public class FleetNotFoundException extends RuntimeException{
    public FleetNotFoundException(String message) {
        super(message);
    }
}
