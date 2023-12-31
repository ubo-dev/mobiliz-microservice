package com.ubo.groupingservice.exception;

import java.time.LocalDateTime;
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details  ;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
