package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class BuildableTypeValidator implements ConstraintValidator<BuildableTypeConstraint, String> {
    @Override
    public void initialize(BuildableTypeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String buildableType, ConstraintValidatorContext ctx) {
        return Arrays.stream(BuildableTypeEnum.values())
                .anyMatch(buildableTypeOption -> buildableTypeOption.toString().equalsIgnoreCase(buildableType));
    }
}
