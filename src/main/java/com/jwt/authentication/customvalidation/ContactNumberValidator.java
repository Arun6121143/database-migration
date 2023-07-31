package com.jwt.authentication.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<CustomValidation,String> {
    @Override
    public void initialize(CustomValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber!= null && phoneNumber.matches("[0-9]+")
                && (phoneNumber.length() > 8) && (phoneNumber.length() < 14);
    }
}