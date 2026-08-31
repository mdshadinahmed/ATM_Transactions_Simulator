package com.bankmanagementsystem.CustomException;

public class AccontNotFoundException extends RuntimeException {
    public AccontNotFoundException(String message) {
        super(message);
    }
}
