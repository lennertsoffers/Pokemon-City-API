package com.lennertsoffers.pokemon_city_api.validation;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.GeometryUtils;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@AllArgsConstructor
public class BuildRoadsValidator implements ConstraintValidator<BuildRoadsConstraint, BuildRoadsDto> {
    private final UserService userService;

    @Override
    public void initialize(BuildRoadsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(BuildRoadsDto buildRoadsDto, ConstraintValidatorContext constraintValidatorContext) {
        List<Buildable> buildableList = userService
                .getAuthUser()
                .getCity()
                .getBuildables();

        return buildRoadsDto
                .locations()
                .stream()
                .noneMatch(roadLocation -> buildableList
                        .stream()
                        .anyMatch(buildable -> GeometryUtils.collidesWith(buildable, roadLocation.getX(), roadLocation.getY(), 1, 1))
                );
    }
}
