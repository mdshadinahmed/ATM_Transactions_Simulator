package com.bankmanagementsystem.CustomException;

public class NegativeNumberFoundException extends RuntimeException {
    public NegativeNumberFoundException(String message) {
        super(message);
    }
}
