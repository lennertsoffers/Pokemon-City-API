package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableType;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for locked buildings for building</b>
 * <p>Valid if the level of the user is equal or higher than the minimum level the user needs to build the buildable</p>
 */
@AllArgsConstructor
public class BuildableBuildLockedValidator implements ConstraintValidator<BuildableBuildLockedConstraint, BuildableBuildDto> {
    private final BuildableService buildableService;
    private final UserService userService;

    @Override
    public void initialize(BuildableBuildLockedConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableBuildDto buildableBuildDto, ConstraintValidatorContext constraintValidatorContext) {
        BuildableType buildableType = buildableService.getBuildableType(buildableBuildDto.buildableType(), buildableBuildDto.name());
        int level = userService.getAuthUser().getLevel();

        return level >= buildableType.getUnlockedAtLevel();
    }
}
