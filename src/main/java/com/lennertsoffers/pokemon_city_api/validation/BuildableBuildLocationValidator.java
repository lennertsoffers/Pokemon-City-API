package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.type.*;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.GeometryUtils;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class BuildableBuildLocationValidator implements ConstraintValidator<BuildableBuildLocationConstraint, BuildableBuildDto> {
    private final UserService userService;
    private final BuildableService buildableService;

    @Override
    public void initialize(BuildableBuildLocationConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableBuildDto buildableBuildDto, ConstraintValidatorContext constraintValidatorContext) {
        BuildableType buildableType = buildableService.getBuildableType(buildableBuildDto.buildableType(), buildableBuildDto.name());

        return userService
                .getAuthUser()
                .getCity()
                .getBuildables()
                .stream()
                .noneMatch(b -> GeometryUtils.collidesWith(b, buildableBuildDto.x(), buildableBuildDto.y(), buildableType.getWidth(), buildableType.getHeight()));
    }
}
