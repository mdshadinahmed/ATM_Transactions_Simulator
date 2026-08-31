package com.bankmanagementsystem.CustomException;

public class NegativeNumbercannotBeAccountID extends RuntimeException {
    public NegativeNumbercannotBeAccountID(String message) {
        super(message);
    }
}
