package com.jwt.authentication.primary.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice implements MapErrorMessage {

    @ExceptionHandler(NoUserInTheDatabaseException.class)
    public Map<String, String> NoUserExceptionHandler(NoUserInTheDatabaseException NoUserInDatabaseException){
        return MapErrorMessage (NoUserInDatabaseException);
    }

    @ExceptionHandler(UserNotFoundWithIdException.class)
    public Map<String ,String> UserNotFoundWithIdExceptionHandler(UserNotFoundWithIdException userNotFoundIdException){
        return MapErrorMessage(userNotFoundIdException);
    }

    @ExceptionHandler(UserExistWithSameEmail.class)
    public Map<String,String> UserExistWithSameEmailExceptionHandler(UserExistWithSameEmail userExistWithSameEmail){
        return MapErrorMessage(userExistWithSameEmail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> contactNumberException(MethodArgumentNotValidException exception){
       Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @Override
    public Map<String,String> MapErrorMessage(Exception exception){
        Map<String,String> erroMap = new HashMap<>();
        erroMap.put("error message", exception.getMessage());
        return erroMap;
    }
}