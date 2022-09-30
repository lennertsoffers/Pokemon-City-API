package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BuildableBuildValidator implements ConstraintValidator<BuildableMoveConstraint, BuildableBuildDto> {

    @Override
    public void initialize(BuildableMoveConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableBuildDto buildableBuildDto, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
