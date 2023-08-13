package com.ubo.mobilizmicroservice.exception;

public class RegistryNotFoundException extends RuntimeException{
    public RegistryNotFoundException(String message) {
        super(message);
    }
}
