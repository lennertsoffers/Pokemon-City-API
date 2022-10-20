package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.GeometryUtils;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for building roads</b>
 * <p>Valid if the new road doesn't collide with any other object in the city</p>
 */
@AllArgsConstructor
public class BuildRoadsValidator implements ConstraintValidator<BuildRoadsConstraint, BuildRoadDto> {
    private final UserService userService;

    @Override
    public void initialize(BuildRoadsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildRoadDto buildRoadDto, ConstraintValidatorContext constraintValidatorContext) {
        return userService
                .getAuthUser()
                .getCity()
                .getBuildables()
                .stream()
                .noneMatch(buildable -> GeometryUtils.collidesWith(buildable, buildRoadDto.location().getX(), buildRoadDto.location().getY(), 2, 2));
    }
}
