package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableMoveDto;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.GeometryUtils;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Validator for moving houses</b>
 * <p>Valid if the new location of the buildable doesn't collide with any other building in the city</p>
 */
@RequiredArgsConstructor
public class BuildableMoveValidator implements ConstraintValidator<BuildableMoveConstraint, BuildableMoveDto> {
    private final UserService userService;
    private final BuildableService buildableService;

    @Override
    public void initialize(BuildableMoveConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildableMoveDto buildableMoveDto, ConstraintValidatorContext constraintValidatorContext) {
        Buildable buildable = buildableService.getById(buildableMoveDto.id());

        return userService
                .getAuthUser()
                .getCity()
                .getBuildables()
                .stream()
                .filter(b -> !b.getId().equals(buildableMoveDto.id()))
                .noneMatch(b -> GeometryUtils.collidesWith(b, buildableMoveDto.x(), buildableMoveDto.y(), buildable.getWidth(), buildable.getHeight()));
    }


}
