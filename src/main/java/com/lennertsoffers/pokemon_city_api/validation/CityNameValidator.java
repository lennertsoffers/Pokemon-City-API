package com.lennertsoffers.pokemon_city_api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for changing the city name</b>
 * <p>Valid if the length of the city name is 2 and 16 letters</p>
 */
public class CityNameValidator implements ConstraintValidator<CityNameConstraint, String> {

    @Override
    public void initialize(CityNameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cityName, ConstraintValidatorContext constraintValidatorContext) {
        return cityName.length() >= 3 && cityName.length() <= 15;
    }
}
