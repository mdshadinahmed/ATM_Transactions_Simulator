package com.bankmanagementsystem.CustomException;

public class NegativeAmountCannotBeDeposit extends RuntimeException {
    public NegativeAmountCannotBeDeposit(String message) {
        super(message);
    }
}
