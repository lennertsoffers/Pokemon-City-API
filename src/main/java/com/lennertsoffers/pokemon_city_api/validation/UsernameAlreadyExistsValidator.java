package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameAlreadyExistsValidator implements ConstraintValidator<UsernameAlreadyExistsConstraint, String> {
    private final UserService userService;

    public UsernameAlreadyExistsValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UsernameAlreadyExistsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        return userService.getUser(username) == null;
    }
}
