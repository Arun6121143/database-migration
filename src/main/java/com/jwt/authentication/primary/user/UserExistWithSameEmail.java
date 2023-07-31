package com.jwt.authentication.primary.user;

public class UserExistWithSameEmail extends Exception {
    public UserExistWithSameEmail(String message) {
        super(message);
    }
}