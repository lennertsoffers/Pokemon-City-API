package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameAlreadyExistsValidator implements ConstraintValidator<UsernameAlreadyExistsConstraint, String> {
    private final UserService userService;

    @Override
    public void initialize(UsernameAlreadyExistsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext ctx) {
        return userService.getUser(username) == null;
    }
}
