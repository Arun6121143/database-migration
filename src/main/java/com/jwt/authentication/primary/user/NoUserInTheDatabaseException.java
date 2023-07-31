package com.jwt.authentication.primary.user;
public class NoUserInTheDatabaseException extends Exception{
    public NoUserInTheDatabaseException(String message) {
        super(message);
    }
}