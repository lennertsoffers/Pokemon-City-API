package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {

    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cityName, ConstraintValidatorContext constraintValidatorContext) {
        return cityName.length() >= 3;
    }
}
