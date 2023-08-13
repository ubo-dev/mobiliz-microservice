package com.ubo.registryservice.exception;

public class RegistryNotFoundException extends RuntimeException{
    public RegistryNotFoundException(String message) {
        super(message);
    }
}
