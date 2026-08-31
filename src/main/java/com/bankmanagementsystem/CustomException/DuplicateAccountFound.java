package com.bankmanagementsystem.CustomException;

public class DuplicateAccountFound extends RuntimeException {
    public DuplicateAccountFound(String message) {
        super(message);
    }
}
