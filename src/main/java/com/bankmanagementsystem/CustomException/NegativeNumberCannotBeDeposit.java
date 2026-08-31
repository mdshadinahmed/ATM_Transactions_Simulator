package com.bankmanagementsystem.CustomException;

public class NegativeNumberCannotBeDeposit extends RuntimeException {
    public NegativeNumberCannotBeDeposit(String message) {
        super(message);
    }
}
