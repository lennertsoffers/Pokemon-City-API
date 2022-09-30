package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableType;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class BuildableBuildEnoughFundsValidator implements ConstraintValidator<BuildableBuildEnoughFundsConstraint, BuildableBuildDto> {
    private final BuildableService buildableService;
    private final UserService userService;

    @Override
    public void initialize(BuildableBuildEnoughFundsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableBuildDto buildableBuildDto, ConstraintValidatorContext constraintValidatorContext) {
        BuildableType buildableType = buildableService.getBuildableType(buildableBuildDto.buildableType(), buildableBuildDto.name());
        int money = userService.getAuthUser().getMoney();

        return money >= buildableType.getPrice();
    }
}
