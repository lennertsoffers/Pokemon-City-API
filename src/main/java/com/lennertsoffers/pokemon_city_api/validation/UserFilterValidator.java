package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.UserFilterDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UserFilterValidator implements ConstraintValidator<UserFilterConstraint, UserFilterDto> {
    @Override
    public void initialize(UserFilterConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserFilterDto userFilterDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userFilterDto.filter() == null) return false;
        if (!userFilterDto.filter().matches("^(.+)(<|>|<=|>=|==)(.+)$")) return false;
        if (!userFilterDto.operation().matches("^(<|>|<=|>=|==)$")) return false;
        if (userFilterDto.field().equals("username")) return userFilterDto.operation().equals("==");
        if (List.of("level", "score").contains(userFilterDto.field())) return userFilterDto.value().matches("^[0-9]+$");

        return false;
    }
}
