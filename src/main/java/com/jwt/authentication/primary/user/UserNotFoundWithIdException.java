package com.jwt.authentication.primary.user;

public class UserNotFoundWithIdException extends Exception {
    public UserNotFoundWithIdException(String message) {
        super(message);
    }
}